package ch17.ex17_05;

interface Resource {
  void use(Object key, Object...args);
  void release();
}
