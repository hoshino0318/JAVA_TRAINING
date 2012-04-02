package ch01.ex01_04;

class Prime {
  /** n 以下の素数を表示する */
  public static void sieve(int n) {
    int[] prime = new int[n];
    boolean[] isPrime = new boolean[n+1];
    for (int i = 0; i <= n; ++i) isPrime[i] = true;
    isPrime[0] = isPrime[1] = false;
    int p = 0;
    for (int i = 2; i <= n; ++i) {
      if (isPrime[i]) {
        prime[p++] = i;
        for (int j = i * 2; j <= n; j += i) isPrime[j] = false;
      }
    }
    
    for (int i = 0; i < p; ++i) {
      System.out.print(prime[i] + " ");
    }
    System.out.println();
  }
  
  public static void main(String[] args) {
    int n = 100;
    sieve(n);
  }
}
