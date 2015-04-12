package unico

import org.grails.cxf.utils.EndpointType

import javax.jws.WebMethod

class SoapService {

    static expose = ['cxfjax']
    def jmsService

    String serviceMethod(String s) {
        return s.toString()
    }

    @WebMethod
    List<Integer> gcdList() {
        return jmsService.browse('jmsGCDQueue')
    }

    @WebMethod
    Integer gcdSum() {
        List<Integer> list = jmsService.browse('jmsGCDQueue')
        return list.sum() as Integer
    }

    @WebMethod
    Integer gcd() {
        Integer head = findOrCreateHead()
        Integer gcd = fetchElementsFromInputQueueAndCalculateGCD(head)
        jmsService.send('jmsGCDQueue', gcd)
        return gcd
    }

    Integer findOrCreateHead() {
        List headQueue = jmsService.browse('jmsHeadQueue')
        Integer head = 0
        if (!headQueue) {
            jmsService.send('jmsHeadQueue', 0)
        } else {
            head = headQueue.last() as Integer
        }
        return head
    }

    Integer fetchElementsFromInputQueueAndCalculateGCD(Integer head) {
        Integer gcd = 0
        List<Integer> list = inputList()
        Integer newHead = head + 2
        Integer maxLimit = list.size()

        if (list && newHead <= maxLimit) {
            gcd = calculateGCD(list[head] as Integer, list[head + 1] as Integer)
            updateHead(newHead)
        }
        return gcd
    }

    Integer calculateGCD(Integer i1, Integer i2) {
        while (i2 > 0) {
            long temp = i2;
            i2 = i1 % i2;
            i1 = temp;
        }
        return i1;
    }

    void updateHead(Integer newHead) {
        jmsService.send('jmsHeadQueue', newHead)
    }

    List<Integer> inputList() {
        return jmsService.browse('jmsInputQueue')
    }
}
