package com.unico

import grails.converters.JSON

class SoapController {

    def jmsService
    def soapService

    def index() {
        render(soapService.listGCDQueue() as JSON)
    }

    def gcd() {
        int gcd = soapService.gcd()
        soapService.resetHead()
        jmsService.send('jmsGCDQueue', gcd)
        render(gcd)
    }

    def gcdList() {
        render(soapService.listGCDQueue() as JSON)
    }

    def gcdSum() {
        List<Integer> list = soapService.listGCDQueue()
        render list.sum()
    }
}
