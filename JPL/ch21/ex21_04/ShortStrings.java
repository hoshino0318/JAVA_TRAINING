package ch21.ex21_04;

import java.util.*;

class ShortStrings implements ListIterator<String> {
  private ListIterator<String> strings; // 元の文字列
  private String nextShort; // 次が不明ならば null
  private String prevShort; // 前が不明ならば null
  private int nextIndex;
  private int size;
  private final int maxLen; // この長さ以下の文字列だけを返す

  public ShortStrings(ListIterator<String> strings, int maxLen) {
    this.strings = strings;
    this.maxLen = maxLen;
    nextShort = null;
    prevShort = null;
    nextIndex = strings.nextIndex() - 1;

    int prevCount = 0;
    while (hasPrevious()) {
      previous();
      prevCount++;
    }
    size = 0;
    while (hasNext()) {
      next();
      size++;
    }
    // インデックスをもとに戻す
    for (int i = 0; i < (size-prevCount); ++i)
      previous();
  }

  @Override
  public boolean hasNext() {
    if (nextShort != null) // すでに見つけている
      return true;
    while (strings.hasNext()) {
      nextShort = strings.next();
      nextIndex++;
      if (nextShort.length() <= maxLen)
        return true;
    }
    nextShort = null; // 見つけられなかった
    return false;
  }

  @Override
  public String next() {
    if (nextShort == null && !hasNext())
      throw new NoSuchElementException();
    String n = nextShort; // nextShort を記録する
    nextShort = null;     // nextShort を消費する
    return n;             // nextShort を返す
  }

  @Override
  public boolean hasPrevious() {
    if (prevShort != null) // すでに見つけている
      return true;
    while (strings.hasPrevious()) {
      prevShort = strings.previous();
      nextIndex--;
      if (prevShort.length() <= maxLen)
        return true;
    }
    prevShort = null; // 見つけられなかった
    return false;
  }

  @Override
  public String previous() {
    if (prevShort == null && !hasPrevious()) {
      throw new NoSuchElementException();
    }
    String p = prevShort; // prevShort を記録する
    prevShort = null;     // prevShort を消費する
    return p;             // prevShort を返す
  }

  @Override
  public int nextIndex() {
    if (hasNext()) {
      return nextIndex;
    } else {
      return size;
    }
  }

  @Override
  public int previousIndex() {
    if (hasPrevious()) {
      return nextIndex;
    } else {
      return -1;
    }
  }

  @Override
  public void remove() {
    if (hasNext()) {
      strings.remove();
    } else if (hasPrevious()) {
      strings.remove();
    } else {
      // 消す対象が見つからなかった
    }
  }

  @Override
  public void set(String str) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void add(String str) {
    throw new UnsupportedOperationException();
  }
}
