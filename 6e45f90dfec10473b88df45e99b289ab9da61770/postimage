# unico
Unico Programming Assignment.

Application name = 'unico'

Application Configurations
-------------------------------------------------

1. Grails Specifications

    version = '2.4.5'

2. Tomcat Specifications

    plugin = ':tomcat'
    version = ':7.0.55'

3. JMS Specifications

    plugin = ':jms'
    version = ':1.3'

    supporting plugin
     plugin = 'org.apache.activemq:activemq-core'
     version = ':5.3.0'

4.  SOAP Specifications

    plugin = ':cxf'
    version = ':2.0.1'

JMS variables
----------------------------------------------------
    jmsQueues

     'jmsInputQueue': To store integer values passed to the push function
     'jmsGCDQueue'  : To store GCD calculated by the gcd method
     'jmsHeadQueue' : To store the current position of the head


Exposed Endpoints
-----------------------------------------------------

1.RESTful methods

   a. POST: /rest/push

          Parameters:
          i1 :: Integer
          i2 :: Integer

          Description:
          The method takes in two Integer parameters (i1 and i2) and pushes them to the 'jmsInputQueue' and responds with the status of the request.

   b. GET: /rest/list

          Description:
          The method lists the 'jmsInputQueue' values


2.SOAPbased endpoints

    a./services/soap/gcd

          Description:

          The method responds with the gcd of the two numbers in the 'jmsInputQueue' in a SOAP based response. The GCD is also pushed into the 'jmsGCDQueue' and the current value in the 'jmsHeadQueue' is incremented to point at the next set of values.

    b./services/soap/gcdList

          Description:

          The method lists the 'jmsGCDQueue' values in a SOAP based response

     c./services/soap/gcdSum

          Description:

          The method responds with the sum of all 'jmsGCDQueue' values in a SOAP based response


-------------------------------------------------------------------------------------------------------

Create EAR:

grails EAR (creates the .ear file in /target folder)
