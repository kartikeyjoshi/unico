package com.unico

import grails.converters.JSON

class SoapController {

    def soapService

    def index() {
        render(soapService.gcdList() as JSON)
    }

    def gcd() {
        render soapService.gcd()
    }

    def gcdList() {
        render(soapService.gcdList() as JSON)
    }

    def gcdSum() {
        render soapService.gcdSum()
    }
}
