@Test public void testResolve_fromAbsolute(){
  Path path=fs.getPath("/foo");
  assertResolvedPathEquals("/foo/bar",path,"bar");
  assertResolvedPathEquals("/foo/bar/baz/test",path,"bar/baz/test");
  assertResolvedPathEquals("/foo/bar/baz/test",path,"bar/baz","test");
  assertResolvedPathEquals("/foo/bar/baz/test",path,"bar","baz","test");
}