package ch14.ex14_08;

class Friendly {
  private Friendly partner;
  private String name;

  public Friendly(String name) {
    this.name = name;
  }

  public synchronized void hug() {
    synchronized (partner) {
      System.out.println(Thread.currentThread().getName() +
          " in " + name + ".hub() trying to invoke " +
          partner.name + ".hubBack()");
      partner.hugBack();
    }
  }

  public synchronized void hugBack() {
    System.out.println(Thread.currentThread().getName() +
        " in " + name + ".hugBack()");
  }

  public void becomeFriend(Friendly partner) {
    this.partner = partner;
  }
}
