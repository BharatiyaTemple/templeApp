/**
 * ActiveEventsLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.troy_temple;

public class ActiveEventsLocator extends org.apache.axis.client.Service implements org.troy_temple.ActiveEvents {

    public ActiveEventsLocator() {
    }


    public ActiveEventsLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ActiveEventsLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ActiveEventsSoap
    private java.lang.String ActiveEventsSoap_address = "http://www.troy-temple.org/ActiveEvents/ActiveEvents.asmx";

    public java.lang.String getActiveEventsSoapAddress() {
        return ActiveEventsSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ActiveEventsSoapWSDDServiceName = "ActiveEventsSoap";

    public java.lang.String getActiveEventsSoapWSDDServiceName() {
        return ActiveEventsSoapWSDDServiceName;
    }

    public void setActiveEventsSoapWSDDServiceName(java.lang.String name) {
        ActiveEventsSoapWSDDServiceName = name;
    }

    public org.troy_temple.ActiveEventsSoap getActiveEventsSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ActiveEventsSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getActiveEventsSoap(endpoint);
    }

    public org.troy_temple.ActiveEventsSoap getActiveEventsSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.troy_temple.ActiveEventsSoapStub _stub = new org.troy_temple.ActiveEventsSoapStub(portAddress, this);
            _stub.setPortName(getActiveEventsSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setActiveEventsSoapEndpointAddress(java.lang.String address) {
        ActiveEventsSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.troy_temple.ActiveEventsSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.troy_temple.ActiveEventsSoapStub _stub = new org.troy_temple.ActiveEventsSoapStub(new java.net.URL(ActiveEventsSoap_address), this);
                _stub.setPortName(getActiveEventsSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ActiveEventsSoap".equals(inputPortName)) {
            return getActiveEventsSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://troy-temple.org/", "ActiveEvents");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://troy-temple.org/", "ActiveEventsSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ActiveEventsSoap".equals(portName)) {
            setActiveEventsSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
