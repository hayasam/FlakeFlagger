@Test public void testParseSubsystem() throws Exception {
  final List<ModelNode> operations=super.parse(getSubsystemXml("subsystem-cmp-key-generators_1_1.xml"));
  assertEquals(5,operations.size());
  assertOperation(operations.get(0),ADD,PathElement.pathElement(SUBSYSTEM,getMainSubsystemName()));
  assertOperation(operations.get(1),ADD,PathElement.pathElement(UUID_KEY_GENERATOR,"uuid1"));
  final ModelNode uuid2=operations.get(2);
  assertOperation(uuid2,ADD,PathElement.pathElement(UUID_KEY_GENERATOR,"uuid2"));
  assertEquals("java:jboss/uuid2",uuid2.get(JNDI_NAME).asString());
  final ModelNode hilo1=operations.get(3);
  assertOperation(hilo1,ADD,PathElement.pathElement(HILO_KEY_GENERATOR,"hilo1"));
  assertEquals("java:/jdbc/DB1",hilo1.get(DATA_SOURCE).asString());
  assertEquals("HILOSEQUENCES1",hilo1.get(TABLE_NAME).asString());
  assertEquals("SEQUENCENAME1",hilo1.get(SEQUENCE_COLUMN).asString());
  assertEquals("HIGHVALUES1",hilo1.get(ID_COLUMN).asString());
  assertEquals("create table HILOSEQUENCES1",hilo1.get(CREATE_TABLE_DDL).asString());
  assertEquals("general1",hilo1.get(SEQUENCE_NAME).asString());
  assertEquals(true,hilo1.get(CREATE_TABLE).asBoolean());
  assertEquals(true,hilo1.get(DROP_TABLE).asBoolean());
  assertEquals(10,hilo1.get(BLOCK_SIZE).asLong());
  final ModelNode hilo2=operations.get(4);
  assertOperation(hilo2,ADD,PathElement.pathElement(HILO_KEY_GENERATOR,"hilo2"));
  assertEquals("java:jboss/hilo2",hilo2.get(JNDI_NAME).asString());
  assertEquals("java:/jdbc/DB2",hilo2.get(DATA_SOURCE).asString());
  assertEquals("HILOSEQUENCES2",hilo2.get(TABLE_NAME).asString());
  assertEquals("SEQUENCENAME2",hilo2.get(SEQUENCE_COLUMN).asString());
  assertEquals("HIGHVALUES2",hilo2.get(ID_COLUMN).asString());
  assertEquals("create table HILOSEQUENCES2",hilo2.get(CREATE_TABLE_DDL).asString());
  assertEquals("general2",hilo2.get(SEQUENCE_NAME).asString());
  assertEquals(false,hilo2.get(CREATE_TABLE).asBoolean());
  assertEquals(false,hilo2.get(DROP_TABLE).asBoolean());
  assertEquals(11,hilo2.get(BLOCK_SIZE).asLong());
}