package com.poplingo.poplingo;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public static List<Models.Country> getAllCountries() {
        List<Models.Country> countries = new ArrayList<>();

        // 國家 1：韓國
        Models.Country korea = new Models.Country("Korea");
        Models.Artist twice = new Models.Artist("TWICE");

        String yesOrYesLyrics = """
            Hey, boy
            Look, I'm gonna make this simple for you
            You got two choices
            Yes or yes?
            Ah 둘 중에 하나만 골라 yes or yes?
            Ah-ah 하나만 선택해 어서 yes or yes?
            내가 이렇게도 이기적이었던가
            뭔가 이렇게 갖고 싶던 적 있었나 (있었나)
            다 놀라 (다 놀라) 내 뻔뻔함에
            Come on and tell me yes
            생각보다 과감해진 나의 시나리오
            이 정도 plan이면 완벽해, 만족해 (만족해)
            I don't care (I don't care)
            누가 뭐래도 ooh
            You better tell me yes
            내 맘은 정했어 yes
            그럼 이제 니 대답을 들을 차례
            힘들면 보기를 줄게, 넌 고르기만 해
            고민할 필요도 없게 해줄게
            뭘 고를지 몰라 준비해 봤어
            둘 중에 하나만 골라 yes or yes?
            니 마음을 몰라 준비해 봤어
            하나만 선택해 어서 yes or yes?
            "싫어"는 싫어, 나 아니면 우리?
            선택을 존중해, 거절은 거절해
            선택지는 하나, 자, 선택은 니 맘
            It's all up to you
            둘 중에 하나만 골라 yes or yes?
            진심일까? Do not guess
            진심이니? Do not ask
            애매한 좌우 말고 확실히 위아래로
            There's no letters N and O
            지워버릴래 오늘부로 (no more)
            복잡하게 고민할 필요 (ooh-ooh)
            없어 정답은 yes, yes, yo
            없던 이기심도 자극하는 너의 눈과
            널 향한 호기심이 만나서
            타올라 (올라), 타오른다
            My heart burn, burn, burn
            You better hurry up
            조금 쉽게 말하자면
            넌 뭘 골라도 날 만나게 될 거야
            뭐 좀 황당하긴 해도 (waiting for you)
            억지라고 해도 (whoa-whoa)
            절대 후회하지 않게 해줄게
            뭘 고를지 몰라 준비해 봤어
            둘 중에 하나만 골라 yes or yes?
            니 마음을 몰라 준비해 봤어
            하나만 선택해 어서 yes or yes?
            "싫어"는 싫어, 나 아니면 우리?
            선택을 존중해, 거절은 거절해
            선택지는 하나, 자, 선택은 니 맘
            Now, it's all up to you
            Maybe not (no, no)
            Maybe yes (no, no)
            좀 더 선명하게 니 맘을 내게 보여봐
            귀 기울여봐, 무슨 소리가 들리지 않니?
            It's simple, Y-E-S, hey
            둘 중에 하나만 골라 yes or yes? (Hey)
            하나만 선택해 어서 yes or yes?
            하나 더 보태서 yes or yes or yes
            골라봐, 자, 선택은 니 맘
            뭘 고를지 몰라 준비해 봤어
            둘 중에 하나만 골라 yes or yes?
            니 마음을 몰라 준비해 봤어
            하나만 선택해 어서 yes or yes?
            "싫어"는 싫어, 나 아니면 우리?
            선택을 존중해, 거절은 거절해
            선택지는 하나, 자, 선택은 니 맘
            It's all up to you
            하나만 선택해 어서 yes or yes?""";

        Models.Song yesOrYes = new Models.Song("Yes or Yes", "yes_or_yes.mp3", yesOrYesLyrics);
        yesOrYes.addVocab(new Models.Vocabulary("선택해", "/seon-taek-hae/", "選擇", "♪ 하나만 선택해 어서 yes or yes?"));
        yesOrYes.addVocab(new Models.Vocabulary("이기적", "/i-gi-jeok/", "自私的", "♪ 내가 이렇게도 이기적이었던가"));
        yesOrYes.addVocab(new Models.Vocabulary("거절해", "/geo-jeol-hae/", "拒絕", "♪ 선택을 존중해, 거절은 거절해"));

        twice.addSong(yesOrYes);
        korea.addArtist(twice);
        countries.add(korea);

        // 國家 2：美國
        Models.Country usa = new Models.Country("USA");
        Models.Artist garrix = new Models.Artist("Martin Garrix");
        Models.Song highOnLife = new Models.Song("High on Life", "high_on_life.mp3", "And I feel so high on life...\nI'd walk a million miles...");
        highOnLife.addVocab(new Models.Vocabulary("High", "/haɪ/", "高昂的", "♪ And I feel so high on life..."));
        highOnLife.addVocab(new Models.Vocabulary("Million", "/ˈmɪljən/", "百萬", "♪ I'd walk a million miles..."));
        garrix.addSong(highOnLife);
        usa.addArtist(garrix);
        countries.add(usa);

        return countries;
    }
}