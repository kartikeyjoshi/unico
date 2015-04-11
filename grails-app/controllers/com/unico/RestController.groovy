package com.unico

import grails.converters.JSON

class RestController {

    static allowedMethods = [index: 'GET', push: "POST", list: 'GET']

    def jmsService

    def index() {
        render(jmsService.browse('jmsInputQueue') as JSON)
    }

    def push() {
        jmsService.send(queue: 'jmsInputQueue', params.i1)
        jmsService.send(queue: 'jmsInputQueue', params.i2)
        render response.status
    }

    def list() {
        render(jmsService.browse('jmsInputQueue') as JSON)
    }

}
