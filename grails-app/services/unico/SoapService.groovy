package unico

import org.grails.cxf.utils.EndpointType

class SoapService {
    static expose = [EndpointType.SIMPLE]
    static excludes = []
    def jmsService

    String serviceMethod(String s) {
        return s.toString()
    }

    List<Integer> listInputQueue() {
        return jmsService.browse('jmsInputQueue')
    }

    List<Integer> gcdList() {
        return jmsService.browse('jmsGCDQueue')
    }

    int gcdSum() {
        List<Integer> list = jmsService.browse('jmsGCDQueue')
        return list.sum()
    }

    int gcd() {
        List<Integer> list = listInputQueue()
        int i1 = list[0]
        int i2 = list[1]

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
        Integer size = listInputQueue().size()
        for (int i = 0; i < size - 2; i++) {
            jmsService.browse('jmsInputQueue')[i] = jmsService.browse('jmsInputQueue')[i + 2];
        }
    }
}
