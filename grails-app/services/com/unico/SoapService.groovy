package com.unico

import grails.transaction.Transactional

@Transactional
class SoapService {

    def jmsService

    List<Integer> listInputQueue() {
        return jmsService.browse('jmsInputQueue')
    }

    List<Integer> listGCDQueue() {
        return jmsService.browse('jmsGCDQueue')
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
        return i1;
    }

    def resetHead() {
        Integer size = listInputQueue().size()
        for (int i = 0; i < size - 2; i++) {
            jmsService.browse('jmsInputQueue')[i] = jmsService.browse('jmsInputQueue')[i + 2];
        }
    }
}
