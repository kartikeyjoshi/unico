package com.unico

import grails.converters.JSON

class RestController {

    static allowedMethods = [index: 'GET', push: "POST", list: 'GET']

    def jmsService

    def index() {
        render(jmsService.browse('jmsInputQueue') as JSON)
    }

    def push() {
        jmsService.send(queue: 'jmsInputQueue', params.i1 as Integer)
        jmsService.send(queue: 'jmsInputQueue', params.i2 as Integer)
        String responseStatus = response.status
        render responseStatus
    }

    def list() {
        render(jmsService.browse('jmsInputQueue') as JSON)
    }

}
