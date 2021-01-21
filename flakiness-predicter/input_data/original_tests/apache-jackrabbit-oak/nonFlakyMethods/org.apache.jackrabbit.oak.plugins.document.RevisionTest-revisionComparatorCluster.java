@Test public void revisionComparatorCluster(){
  RevisionComparator comp=new RevisionComparator(0);
  Revision r0c1=new Revision(0x010,0,1);
  Revision r0c2=new Revision(0x010,0,2);
  Revision r1c1=new Revision(0x110,0,1);
  Revision r2c1=new Revision(0x120,0,1);
  Revision r3c1=new Revision(0x130,0,1);
  Revision r1c2=new Revision(0x100,0,2);
  Revision r2c2=new Revision(0x200,0,2);
  Revision r3c2=new Revision(0x300,0,2);
  assertEquals(1,comp.compare(r1c1,r1c2));
  assertEquals(-1,comp.compare(r2c1,r2c2));
  assertEquals(-1,comp.compare(r3c1,r3c2));
  comp.add(r2c1,new Revision(0x20,0,0));
  comp.add(r2c2,new Revision(0x10,0,0));
  assertEquals("1:\n r120-0-1:r20-0-0\n" + "2:\n r200-0-2:r10-0-0\n",comp.toString());
  assertEquals(-1,comp.compare(r0c1,r0c2));
  assertEquals(1,comp.compare(r1c1,r1c2));
  assertEquals(1,comp.compare(r2c1,r2c2));
  assertEquals(-1,comp.compare(r3c1,r3c2));
  comp.add(r3c1,new Revision(0x30,0,0));
  comp.add(r3c2,new Revision(0x30,0,0));
  assertEquals("1:\n r120-0-1:r20-0-0 r130-0-1:r30-0-0\n" + "2:\n r200-0-2:r10-0-0 r300-0-2:r30-0-0\n",comp.toString());
  assertEquals(1,comp.compare(r1c1,r1c2));
  assertEquals(1,comp.compare(r2c1,r2c2));
  assertEquals(-1,comp.compare(r3c1,r3c2));
  assertEquals(-1,comp.compare(r1c2,r1c1));
  assertEquals(-1,comp.compare(r2c2,r2c1));
  assertEquals(1,comp.compare(r3c2,r3c1));
  comp.purge(0x10);
  assertEquals("1:\n r120-0-1:r20-0-0 r130-0-1:r30-0-0\n" + "2:\n r300-0-2:r30-0-0\n",comp.toString());
  comp.purge(0x20);
  assertEquals("1:\n r130-0-1:r30-0-0\n" + "2:\n r300-0-2:r30-0-0\n",comp.toString());
  comp.add(new Revision(0x301,1,2),new Revision(0x30,0,0));
  assertEquals("1:\n r130-0-1:r30-0-0\n" + "2:\n r301-1-2:r30-0-0\n",comp.toString());
  comp.purge(0x30);
  assertEquals("",comp.toString());
}