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
        List<Integer> list = jmsService.browse('jmsGCDQueue')
        return list
    }

    @WebMethod
    int gcdSum() {
        List<Integer> list = jmsService.browse('jmsGCDQueue')
        return list.sum()
    }

    @WebMethod
    int gcd() {
        List<Integer> list = inputList()
        int i1 = list[0] as int
        int i2 = list[1] as int

        while (i2 > 0) {
            long temp = i2;
            i2 = i1 % i2; // % is remainder
            i1 = temp;
        }
        resetHead()
        jmsService.send('jmsGCDQueue', i1)
        return i1;
    }

    def resetHead() {
        Integer size = inputList().size()
        for (int i = 0; i < size - 2; i++) {
            jmsService.browse('jmsInputQueue')[i] = jmsService.browse('jmsInputQueue')[i + 2];
        }
    }

    List<Integer> inputList() {
        return jmsService.browse('jmsInputQueue')
    }

}
