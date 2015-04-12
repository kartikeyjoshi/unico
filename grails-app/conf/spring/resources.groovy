// Place your Spring DSL code here
beans = {
    jmsConnectionFactory(org.apache.activemq.ActiveMQConnectionFactory) {
        brokerURL = 'vm://' + grailsApplication.config.grails.serverURL
    }
}
