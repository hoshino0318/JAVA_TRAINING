package ch01.ex01_14;

class Walkman {
  private static final int MAX_MUSIC_NUM = 1000;  
  private String[] musicSet; // 音楽セット
  private int nowPlayingIndex; /* 現在再生中の Music を表す(-1 - MAX_MUSIC_NUM-1)
                                * -1 で停止を表す*/

  public Walkman() {
    musicSet = new String[MAX_MUSIC_NUM];
    nowPlayingIndex = -1; // 停止中
  }

  /** index で指定した音楽が削除できるか判定する */
  private booleand canRemoveMusic(int index) {
    if (index < 0 || index >= music.length) {
      return false;
    } else if (nowPlayingIndex == index) { // 指定した音楽が再生中
      return false;
    }    
    return true;
  }

  /** music を音楽セットに追加する
    * 音楽セットがいっぱいの場合は -1 を返す */
  public int addMusic(Strng music) {
    for (int i = 0; i < musicSet.length; ++i) {
      if (musicSet[i] == null) {
        musicSet[i] = music;
        return i;
      }
    }
    return -1; // 音楽を追加出来なかった
  }

  /** index で指定した曲を音楽セットから削除する
   *  削除した音楽を返す */  
  public String removeMusic(int index) {
    if ( !canRemoveMusic(index) ) {
      return null;
    } else if (musicSet[index] == null) { // 音楽が存在しない
      return null;
    }

    String retMusic = musicSet[index];
    musicSet[index] = null;
    return retMusic;
  }

  /** music で指定した曲を音楽セットから削除する
   *  削除した音楽を返す */  
  public String[] removeMusic(String music) {
    int index = -1;
    for (int i = 0; i < musicSet.length; ++i) {
      if (musicSet[i].equals(music)) {
        index = i;
        break;
      }
    }    
    if (index == -1) return null;
    
    return removeMusic(index);
  }

  /** 音楽セットを返す */  
  public String[] getMusicSet() {
    return musicSet;
  }

  /** index で指定した音楽を再生する */
  public void playMusic(int index) {
    if (index < 0 || index >= music.length) {
      System.out.println("Invalid index");
    } else if (musicSet[index] == null) {
      System.out.println("The music with index does not exit");
    }
    nowPlayingIndex = index;
    System.out.println("Play music: " + musicSet[index]);
  }

  /** 音楽を停止する */
  public void stopMusic() {
    nowPlayingIndex = -1;
    System.out.println("Stop music");
  }
}
