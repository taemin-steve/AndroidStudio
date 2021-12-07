package com.example.gustomap;

import com.google.android.gms.maps.model.LatLng;

public class MyItem {
    String[] titles = {
            "리리코", "유니의 우아한식탁 ", "버바나", "감칠", "와이탑", "올더플레이트", "심원", "미담진족", "더 피자 보이즈", "홍정기 참치",
            // 연남 index 0~9
            "골뱅이신사", "우니도", "신사 치킨 클럽", "신사의 바램", "콘피에르", "무탄",
            // 신사 10~15
            "스시롭다", "포크댄스", "베트남이랑", "조양관", "스카이가든", "르띠",
            // 강남 16~21
            "경양카츠", "워터밀", "저스트텐동", "실낙원", "기러기 둥지", "송암여관", "을지깐깐", "을지다락",
            //익선 22~29
    };
    Integer[] images = {
            R.drawable.ririco, R.drawable.uni, R.drawable.burbana, R.drawable.gamchil, R.drawable.whytop, R.drawable.alltheplate, R.drawable.simone, R.drawable.midam, R.drawable.thepizzaboys, R.drawable.hong,
            // 연남 index 0~9
            R.drawable.goal, R.drawable.unido, R.drawable.sinsaclub, R.drawable.barlamb, R.drawable.confere, R.drawable.jokbal, R.drawable.mutan,
            // 신사 9~15
            R.drawable.susi, R.drawable.porkdance, R.drawable.batnam, R.drawable.joyang, R.drawable.skygarden, R.drawable.letti,
            // 강남 16~21
            R.drawable.kungyang, R.drawable.watermeal, R.drawable.justtd, R.drawable.narkone, R.drawable.kirukinest, R.drawable.songarm, R.drawable.kankan, R.drawable.darrock,
            //익선 22~29
    };
    String[] rinks = {
            "https://www.instagram.com/p/CI5lInDFK69/", "https://www.instagram.com/p/CLy4jF1FT4g/?utm_medium=copy_link", "https://www.instagram.com/bbang_byeol/p/COW-ST7lnuH/?utm_medium=copy_link", "https://www.instagram.com/bbang_byeol/p/CLvV0iTlkXt/?utm_medium=copy_link", "https://www.instagram.com/p/CVcoR9HFg6H/?utm_medium=copy_link", "https://www.instagram.com/p/CNt_c7Pligy/?utm_medium=copy_link", "https://www.instagram.com/p/CMjMffpFLj4/?utm_medium=copy_link", "https://www.instagram.com/p/CWabS3YFjy8/?utm_medium=copy_link", "https://www.instagram.com/bbang_byeol/p/CKtXURPFYdd/?utm_medium=copy_link", "https://www.instagram.com/bbang_byeol/p/CRgJJqgliGT/?utm_medium=copy_link",
            // 연남 index 0~8
            "https://www.instagram.com/p/CVzKI0QFrrG/?utm_medium=copy_link", "https://www.instagram.com/p/CQsNY-EFZa7/?utm_medium=copy_link", "https://www.instagram.com/p/CSD8aqNFYdR/?utm_medium=copy_link", "https://www.instagram.com/bbang_byeol/p/CSgAYyhFDsX/?utm_medium=copy_link", "https://www.instagram.com/p/CW2MiAAlYnU/?utm_medium=copy_link", "https://www.instagram.com/p/CQkk6F4FLIA/?utm_medium=copy_link", "https://www.instagram.com/p/CTEi769F_g1/?utm_medium=copy_link",
            // 신사 9~15
            "https://www.instagram.com/p/CQfOEHoFOem/?utm_medium=copy_link", "https://www.instagram.com/p/CU9W9JBlgLe/?utm_medium=copy_link", "https://www.instagram.com/bbang_byeol/p/CL-3t79lpWZ/?utm_medium=copy_link", "https://www.instagram.com/bbang_byeol/p/COeqlzAlrhO/?utm_medium=copy_link", "https://www.instagram.com/p/CPVAZoMlyvJ/?utm_medium=copy_link", "https://www.instagram.com/p/CPuYqv_loVQ/?utm_medium=copy_link",
            // 강남 16~21
            "https://www.instagram.com/bbang_byeol/p/CUHsGJRlmF7/?utm_medium=copy_link", "https://www.instagram.com/p/CO7fEpjFGRG/?utm_medium=copy_link", "https://www.instagram.com/p/CNmv_3sF135/?utm_medium=copy_link", "https://www.instagram.com/p/CM3hQUYlixR/?utm_medium=copy_link", "https://www.instagram.com/bbang_byeol/p/CJQi1MZFDs1/?utm_medium=copy_link", "https://www.instagram.com/bbang_byeol/p/CO2IwsgFTur/?utm_medium=copy_link", "https://www.instagram.com/p/CSL-aywljWz/?utm_medium=copy_link", "https://www.instagram.com/bbang_byeol/p/CTMaOkwFiaj/?utm_medium=copy_link",
            //익선 22~29
    };
    LatLng[] postion ={

            new LatLng(37.5627755302724, 126.92577299981137),
            new LatLng(37.56170453294817, 126.92588924929144),
            new LatLng(37.56004757980495, 126.92273578679354),
            new LatLng(37.562233464453506, 126.92626277552715),
            new LatLng(37.56487337120603, 126.92425412194287),
            new LatLng(37.56190496443423, 126.92697759453657),
            new LatLng(37.565331809807326, 126.92302941093413),
            new LatLng(37.550373767221394, 126.91979156006867),
            new LatLng(37.55526920057066, 126.92604843083261),
            new LatLng(37.55983878955845, 126.92125987399774),
            new LatLng(37.51861436582865, 127.02182552207593),
            new LatLng(37.52058167158564, 127.01996440875325),
            new LatLng(37.52069174133215, 127.02161801826435),
            new LatLng(37.51807038756131, 127.02051658867458),
            new LatLng(337.52355499608041, 127.03753683402687),
            new LatLng(37.527268835570176, 127.03029762214186),
            new LatLng(37.50848047624522, 127.02370468202719),
            new LatLng(37.507042867612896, 127.0256736211936),
            new LatLng(37.49888667096547, 127.0260391015097),
            new LatLng(37.49491124694332, 127.02744788286444),
            new LatLng(37.50682950791088, 127.10098464165786),
            new LatLng(37.50536389298474, 127.10571169195774),
            new LatLng(37.57287488902816, 126.98936155196962),
            new LatLng(37.57340554777885, 126.99007247982176),
            new LatLng(37.57307445345555, 126.9898663324465),
            new LatLng(37.57283259994473, 126.98800058780684),
            new LatLng(37.57275690181212, 126.99002356383696),
            new LatLng(37.57397672059227, 126.99003192890474),
            new LatLng(37.5657251909507, 126.99106473589237),
            new LatLng(37.563703967784996, 126.99141362398069),
    };
}
