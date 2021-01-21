@Test public void testHeartbeat() throws Exception {
  ActionManager am=new ActionManager(0,0,null,null,new ActionDBInMemoryImpl());
  Clusters fsm=clusters;
  String hostname="host1";
  fsm.addHost(hostname);
  Host hostObject=clusters.getHost(hostname);
  hostObject.setIPv4("ipv4");
  hostObject.setIPv6("ipv6");
  hostObject.setOsType("centos5");
  ActionQueue aq=new ActionQueue();
  HeartBeatHandler handler=new HeartBeatHandler(fsm,aq,am,injector);
  Register reg=new Register();
  HostInfo hi=new HostInfo();
  hi.setHostName("host1");
  hi.setOS("CentOS");
  hi.setOSRelease("5.8");
  reg.setHostname(hostname);
  reg.setHardwareProfile(hi);
  handler.handleRegistration(reg);
  hostObject.setState(HostState.UNHEALTHY);
  ExecutionCommand execCmd=new ExecutionCommand();
  execCmd.setCommandId("2-34");
  execCmd.setHostname(hostname);
  aq.enqueue(hostname,new ExecutionCommand());
  HeartBeat hb=new HeartBeat();
  hb.setResponseId(0);
  hb.setNodeStatus(new HostStatus(Status.HEALTHY,"I am ok"));
  hb.setHostname(hostname);
  handler.handleHeartBeat(hb);
  assertEquals(HostState.HEALTHY,hostObject.getState());
  assertEquals(0,aq.dequeueAll(hostname).size());
}