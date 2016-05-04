package org.troy_temple;

public class ActiveEventsSoapProxy implements org.troy_temple.ActiveEventsSoap {
  private String _endpoint = null;
  private org.troy_temple.ActiveEventsSoap activeEventsSoap = null;
  
  public ActiveEventsSoapProxy() {
    _initActiveEventsSoapProxy();
  }
  
  public ActiveEventsSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initActiveEventsSoapProxy();
  }
  
  private void _initActiveEventsSoapProxy() {
    try {
      activeEventsSoap = (new org.troy_temple.ActiveEventsLocator()).getActiveEventsSoap();
      if (activeEventsSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)activeEventsSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)activeEventsSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (activeEventsSoap != null)
      ((javax.xml.rpc.Stub)activeEventsSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.troy_temple.ActiveEventsSoap getActiveEventsSoap() {
    if (activeEventsSoap == null)
      _initActiveEventsSoapProxy();
    return activeEventsSoap;
  }
  
  public java.lang.String getTop5ActiveEvents() throws java.rmi.RemoteException{
    if (activeEventsSoap == null)
      _initActiveEventsSoapProxy();
    return activeEventsSoap.getTop5ActiveEvents();
  }
  
  
}