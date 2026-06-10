-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 伺服器版本： 10.4.24-MariaDB
-- PHP 版本： 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `poplingo_db`
--

-- --------------------------------------------------------

--
-- 資料表結構 `artists`
--

CREATE TABLE `artists` (
  `id` int(11) NOT NULL,
  `country_id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `artists`
--

INSERT INTO `artists` (`id`, `country_id`, `name`) VALUES
(1, 1, 'TWICE'),
(2, 2, 'Uru'),
(3, 3, 'The San Miguel Master Chorale'),

-- --------------------------------------------------------

--
-- 資料表結構 `countries`
--

CREATE TABLE `countries` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `countries`
--

INSERT INTO `countries` (`id`, `name`) VALUES
(1, 'Korea'),
(2, 'Japan'),
(3, 'Philippines'),
(4, 'United States'),
(5, 'United Kingdom'),
(6, 'Canada'),
(7, 'Germany'),
(8, 'France'),
(9, 'Australia'),

-- --------------------------------------------------------

--
-- 資料表結構 `songs`
--

CREATE TABLE `songs` (
  `id` int(11) NOT NULL,
  `artist_id` int(11) NOT NULL,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `audio_file_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `full_lyrics` text COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `songs`
--

INSERT INTO `songs` (`id`, `artist_id`, `title`, `audio_file_name`, `full_lyrics`) VALUES
(2, 2, 'そばにいるよ', 'sobani_iruyo.mp3', '[00:00.00]Uru - そば に いる よ\n[00:11.50]出会い 話して 恋 に 落ちて 気付けば 隣 で\n[00:23.20]声 を 聞いて いられる こと とても 当たり前 で\n[00:34.50]愛 を 捨て 生きて いけ なんて できない の\n[00:40.40]それでも それでも これ で いい の ？\n[00:46.30]昨日 まで の 正しさ とか 考えてる\n[00:58.20]健やかなる とき 病める 時 も\n[01:04.10]私 は 私 で 居られます ように\n[01:10.00]何 も できなく なった としても\n[01:15.80]ただ 、 そば に いる よ\n[01:21.80]ただ 、 そば に いる よ\n[01:36.50]少し 遠く に でかけよう か 歳 を とる 前 に\n[01:48.30]見た こと ない もの 思い出 の 場所\n[01:54.20]何でも いい のに\n[01:59.60]愛 は 何故 生きて いけ なんて 言う の かな\n[02:05.40]何度 も 何度 も くじけそう で\n[02:11.40]昨日 まで の 優しさ とか 思い出して\n[02:23.20]健やかなる とき 病める 時 も\n[02:29.10]あなた は あなた で 居られます ように\n[02:35.00]何 も できなく なった としても\n[02:40.90]ただ 、 そば に いる よ\n[02:47.00]月 が 陰る ように 胸 が 痛んで も\n[02:53.00]一人 じゃ ない から\n[02:58.00]ほんの 少し で いい 私 が ここ に\n[03:04.20]居る 理由 に なる なら\n[03:09.50]健やかなる とき 病める 時 も\n[03:15.50]私 は 私 で 居られます ように\n[03:21.40]何 も できなく なった としても\n[03:27.20]ただ 、 そば に いる よ\n[03:33.20]健やかなる とき 病める 時 も\n[03:39.10]あなた と 私 で 居られます ように\n[03:45.00]例え 灯り が 消えた としても\n[03:50.80]ただ 、 そば に いる よ\n[03:56.80]ただ 、 そば に いる よ'),
(3, 3, 'Da Coconut Nut', 'da_coconut_nut.mp3', '[00:00.00]The San Miguel Master Chorale - Da Coconut Nut\r\n[00:15.00]Coco-nut, co-co-co-co-nut (Nut-nut)\r\n[00:18.00]Coco-nut, co-co-co-co-nut (Nut-nut)\r\n[00:22.00]Coco-nut, co-co-co-co-nut (Nut-nut)\r\n[00:25.50]Coco-nut, co-co-co-co-nut\r\n[00:28.50]The coconut nut is a giant nut\r\n[00:32.00]If you eat too much, you\'ll get very fat\r\n[00:35.50]Now, the coconut nut is a big, big nut\r\n[00:39.00]But this delicious nut is not a nut\r\n[00:43.00]It\'s the coco fruit (it\'s the coco fruit)\r\n[00:46.50]Of the coco tree (of the coco tree)\r\n[00:50.00]From the coco palm family\r\n[00:54.00]There are so many uses of the coconut tree\r\n[00:57.50]You can build a big house for the family\r\n[01:01.00]All you need is to find a coconut man\r\n[01:04.50]If he cuts the tree, he gets the fruit free\r\n[01:08.50]It\'s the coco fruit (it\'s the coco fruit)\r\n[01:12.00]Of the coco tree (of the coco tree)\r\n[01:15.50]From the coco palm family\r\n[01:19.50]Coco-nut, co-co-co-co-nut (Nut-nut)\r\n[01:23.00]Coco-nut, co-co-co-co-nut\r\n[01:26.50]The coconut bark for the kitchen floor\r\n[01:30.00]If you save some of it, you can build a door\r\n[01:34.00]Now, the coconut trunk, do not throw this junk\r\n[01:37.50]If you save some of it, you\'ll have a second floor\r\n[01:41.00]The coconut wood is very good\r\n[01:44.50]It can stand 20 years if you pray it would\r\n[01:48.00]Now, the coconut root, to tell you the truth\r\n[01:51.50]You can throw it or use it as firewood\r\n[01:55.50]The coconut leaves, good shade it gives\r\n[01:59.00]For the roof, for the walls up against the eaves\r\n[02:02.50]Now, the coconut fruit, say my relatives\r\n[02:06.00]Make good cannonballs up against the thieves\r\n[02:10.00]It\'s the coco fruit (it\'s the coco fruit)\r\n[02:13.50]Of the coco tree (of the coco tree)\r\n[02:17.00]From the coco palm family\r\n[02:21.00]The coconut nut is a giant nut\r\n[02:24.50]If you eat too much, you\'ll get very fat\r\n[02:28.00]Now, the coconut nut is a big, big nut\r\n[02:31.50]But this delicious nut is not a nut\r\n[02:35.50]The coconut nut is a giant nut\r\n[02:39.00]If you eat too much, you\'ll get very fat\r\n[02:42.50]Now, the coconut nut is a big, big nut\r\n[02:46.00]But this delicious nut is not a nut\r\n[02:50.00]It\'s the coco fruit (it\'s the coco fruit)\r\n[02:53.00]Of the coco tree (of the coco tree)\r\n[02:56.50]From the coco palm family\r\n[03:00.50]It\'s the coco fruit (it\'s the coco fruit)\r\n[03:04.00]Of the coco tree (of the coco tree)\r\n[03:07.50]From the coco palm family\r\n[03:11.00]It\'s the coco fruit (it\'s the coco fruit)\r\n[03:14.50]Of the coco tree (of the coco tree)\r\n[03:18.00]From the coco palm family\r\n[03:22.00]La la la la la... Olé!'),

-- --------------------------------------------------------

--
-- 資料表結構 `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'user001', '123456');

-- --------------------------------------------------------

--
-- 資料表結構 `vocabularies`
--

CREATE TABLE `vocabularies` (
  `id` int(11) NOT NULL,
  `song_id` int(11) NOT NULL,
  `word` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phonetic` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `translation` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lyric_context` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_starred` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `vocabularies`
--

INSERT INTO `vocabularies` (`id`, `song_id`, `word`, `phonetic`, `translation`, `lyric_context`, `is_starred`) VALUES
(413, 2, '出会い', 'であい', '相遇、邂逅', '♪ 出会い 話して 恋 に 落ちて', 0),
(414, 2, '話して', 'はなして', '說話、聊天', '♪ 出会い 話して 恋 に 落ちて', 0),
(415, 2, '恋', 'こい', '戀愛', '♪ 恋 に 落ちて', 0),
(416, 2, 'に', 'ni', '在... / 向... (助詞)', '♪ 恋 に 落ちて', 0),
(417, 2, '落ちて', 'おちて', '墜入、陷入', '♪ 恋 に 落ちて', 1),
(418, 2, '気付けば', 'きづけば', '注意到的話、回過神來', '♪ 気付けば 隣 で', 0),
(419, 2, '隣', 'となり', '旁邊', '♪ 気付けば 隣 で', 0),
(420, 2, 'で', 'de', '在... / 用... (助詞)', '♪ 気付けば 隣 で', 0),
(421, 2, '声', 'こえ', '聲音', '♪ 声 を 聞いて', 0),
(422, 2, 'を', 'wo', '把、將 (受詞標記)', '♪ 声 を 聞いて', 0),
(423, 2, '聞いて', 'きいて', '聽', '♪ 声 を 聞いて', 0),
(424, 2, 'いられる', 'いられる', '能保持、能持續', '♪ 聞いて いられる こと', 0),
(425, 2, 'こと', 'koto', '事情、事物', '♪ 聞いて いられる こと', 0),
(426, 2, 'とても', 'totemo', '非常', '♪ とても 当たり前 で', 0),
(427, 2, '当たり前', 'あたりまえ', '理所當然', '♪ とても 当たり前 で', 1),
(428, 2, '愛', 'あい', '愛', '♪ 愛 を 捨て', 0),
(429, 2, '捨て', 'すて', '捨棄', '♪ 愛 を 捨て', 0),
(430, 2, '生きて', 'いきて', '活著', '♪ 生きて いけ', 0),
(431, 2, 'いけ', 'ike', '去 (生存下去)', '♪ 生きて いけ', 1),
(432, 2, 'なんて', 'nante', '之類的', '♪ いけ なんて できない の', 0),
(433, 2, 'できない', 'dekinai', '做不到', '♪ なんて できない の', 0),
(434, 2, 'の', 'no', '的 / (語氣詞)', '♪ できない の', 0),
(435, 2, 'それでも', 'soredemo', '儘管如此', '♪ それでも それでも これ で いい の ？', 0),
(436, 2, 'これ', 'kore', '這個', '♪ これ で いい の ？', 0),
(437, 2, 'いい', 'ii', '好、可以', '♪ これ で いい の ？', 0),
(438, 2, '昨日', 'きのう', '昨天', '♪ 昨日 まで の 正しさ', 0),
(439, 2, 'まで', 'made', '直到...', '♪ 昨日 まで の 正しさ', 0),
(440, 2, '正しさ', 'ただしさ', '正確性', '♪ 昨日 まで の 正しさ', 0),
(441, 2, 'とか', 'toka', '之類的', '♪ 正しさ とか 考えてる', 0),
(442, 2, '考えてる', 'かんがえてる', '正在思考', '♪ 正しさ とか 考えてる', 0),
(443, 2, '健やかなる', 'すこやかなる', '健康的', '♪ 健やかなる とき', 0),
(444, 2, 'とき', 'toki', '時候', '♪ 健やかなる とき', 0),
(445, 2, '病める', 'やめる', '生病的', '♪ 病める 時 も', 0),
(446, 2, '時', 'とき', '時候', '♪ 病める 時 も', 0),
(447, 2, 'も', 'mo', '也 (助詞)', '♪ 病める 時 も', 0),
(448, 2, '私', 'わたし', '我', '♪ 私 は 私 で 居られます ように', 0),
(449, 2, 'は', 'wa', '是 (主題標記)', '♪ 私 は 私 で', 0),
(450, 2, '居られます', 'いられます', '能存在、能待著', '♪ 居られます ように', 0),
(451, 2, 'ように', 'youni', '祈願、為了...', '♪ 居られます ように', 0),
(452, 2, '何', 'なに', '什麼', '♪ 何 も できなく なった としても', 0),
(453, 2, 'できなく', 'できなく', '無法做到', '♪ 何 も できなく なった', 0),
(454, 2, 'なった', 'なった', '變成 (過去式)', '♪ できなく なった としても', 0),
(455, 2, 'としても', 'toshitemo', '即使', '♪ できなく なった としても', 0),
(456, 2, 'ただ', 'tada', '只是', '♪ ただ 、 そば に いる よ', 1),
(457, 2, 'そば', 'soba', '身邊', '♪ ただ 、 そば に いる よ', 0),
(458, 2, 'いる', 'iru', '在、存在', '♪ そば に いる よ', 0),
(459, 2, 'よ', 'yo', '喔 (語氣詞)', '♪ そば に いる よ', 0),
(460, 2, '少し', 'すこし', '稍微', '♪ 少し 遠く に', 0),
(461, 2, '遠く', 'とおく', '遠方、遠處', '♪ 少し 遠く に', 0),
(462, 2, 'でかけよう', 'でかけよう', '出門吧', '♪ でかけよう か', 0),
(463, 2, 'か', 'ka', '嗎 (疑問詞)', '♪ でかけよう か', 0),
(464, 2, '歳', 'とし', '年紀、歲數', '♪ 歳 を とる 前 に', 0),
(465, 2, 'とる', 'とる', '取得、增加', '♪ 歳 を とる 前 に', 0),
(466, 2, '前', 'まえ', '之前', '♪ 歳 を とる 前 に', 0),
(467, 2, '見た', 'みた', '看過', '♪ 見た こと ない もの', 0),
(468, 2, 'ない', 'ない', '沒有', '♪ 見た こと ない もの', 0),
(469, 2, 'もの', 'mono', '事物', '♪ 見た こと ない もの', 0),
(470, 2, '思い出', 'おもいで', '回憶', '♪ 思い出 の 場所', 0),
(471, 2, '場所', 'ばしょ', '場所、地方', '♪ 思い出 の 場所', 0),
(472, 2, '何でも', 'なんでも', '什麼都', '♪ 何でも いい のに', 0),
(473, 2, 'のに', 'noni', '明明...', '♪ 何でも いい のに', 0),
(474, 2, '何故', 'なぜ', '為什麼', '♪ 愛 は 何故', 0),
(475, 2, '言う', 'いう', '說', '♪ なんて 言う の かな', 0),
(476, 2, 'かな', 'kana', '吧 (疑問/猜測)', '♪ なんて 言う の かな', 0),
(477, 2, '何度', 'なんど', '好幾次', '♪ 何度 も 何度 も くじけそう で', 0),
(478, 2, 'くじけそう', 'くじけそう', '快要受到挫折', '♪ くじけそう で', 0),
(479, 2, '優しさ', 'やさしさ', '溫柔', '♪ 優しさ とか 思い出して', 0),
(480, 2, '思い出して', 'おもいだして', '回想起來', '♪ 優しさ とか 思い出して', 0),
(481, 2, 'あなた', 'anata', '你', '♪ あなた は あなた で', 0),
(482, 2, '月', 'つき', '月亮', '♪ 月 が 陰る ように', 0),
(483, 2, 'が', 'ga', '是 (主語標記)', '♪ 月 が 陰る ように', 0),
(484, 2, '陰る', 'かげる', '變暗、被遮蔽', '♪ 月 が 陰る ように', 0),
(485, 2, '胸', 'むね', '胸口、心', '♪ 胸 が 痛んで も', 0),
(486, 2, '痛んで', 'いたんで', '疼痛', '♪ 胸 が 痛んで も', 0),
(487, 2, '一人', 'ひとり', '一個人', '♪ 一人 じゃ ない から', 0),
(488, 2, 'じゃ', 'ja', '不是', '♪ 一人 じゃ ない から', 0),
(489, 2, 'から', 'kara', '因為 / 從...', '♪ 一人 じゃ ない から', 0),
(490, 2, 'ほんの', 'honno', '僅僅', '♪ ほんの 少し で いい', 0),
(491, 2, 'ここ', 'koko', '這裡', '♪ 私 が ここ に', 0),
(492, 2, '理由', 'りゆう', '理由、原因', '♪ 居る 理由 に なる なら', 0),
(493, 2, 'なる', 'naru', '變成', '♪ 理由 に なる なら', 0),
(494, 2, 'なら', 'nara', '的話 (條件)', '♪ 理由 に なる なら', 0),
(495, 2, 'と', 'to', '和、與', '♪ あなた と 私 で', 0),
(496, 2, '例え', 'たとえ', '即使、就算', '♪ 例え 灯り が 消えた としても', 0),
(497, 2, '灯り', 'あかり', '燈光', '♪ 例え 灯り が 消えた としても', 0),
(498, 2, '消えた', 'きえた', '消失、熄滅', '♪ 灯り が 消えた としても', 0),
(499, 4, 'あと', 'ato', '還有、之後', '♪ あと 一粒 の 涙 で', 0),
(500, 4, '一粒', 'ひとつぶ', '一粒、一顆 (多指眼淚)', '♪ あと 一粒 の 涙 で', 0),
(501, 4, '涙', 'なみだ', '眼淚', '♪ あと 一粒 の 涙 で', 0),
(502, 4, 'ひと言', 'ひとこと', '一句話', '♪ ひと言 の 勇気 で', 0),
(503, 4, '勇気', 'ゆうき', '勇氣', '♪ ひと言 の 勇気 で', 0),
(504, 4, '願い', 'ねがい', '願望', '♪ 願い が かなう', 0),
(505, 4, 'かなう', 'かなう', '實現', '♪ 願い が かなう', 1),
(506, 4, 'その', 'sono', '那個', '♪ その 時 が 来る って', 0),
(507, 4, '来る', 'くる', '到來', '♪ その 時 が 来る って', 0),
(508, 4, 'って', 'tte', '說、所謂', '♪ その 時 が 来る って', 0),
(509, 4, '僕', 'ぼく', '我 (男性用語)', '♪ 僕 は 信じてる から', 0),
(510, 4, '信じてる', 'しんじてる', '相信著', '♪ 僕 は 信じてる から', 0),
(511, 4, '君', 'きみ', '你', '♪ 君 も あきらめないで いて', 0),
(512, 4, 'あきらめないで', 'あきらめないで', '請不要放棄', '♪ 君 も あきらめないで いて', 0),
(513, 4, 'いて', 'ite', '保持、待著', '♪ 君 も あきらめないで いて', 0),
(514, 4, 'この', 'kono', '這個', '♪ この 両手 を あの 空 へ', 0),
(515, 4, '両手', 'りょうて', '雙手', '♪ この 両手 を あの 空 へ', 0),
(516, 4, 'あの', 'ano', '那個', '♪ あの 空 へ', 0),
(517, 4, '空', 'そら', '天空', '♪ あの 空 へ', 0),
(518, 4, 'へ', 'he', '往、向 (助詞)', '♪ あの 空 へ', 0),
(519, 4, '日', 'ひ / nichi', '日子、天', '♪ あの 日 も こんな 夏 だった', 0),
(520, 4, 'こんな', 'konna', '這樣的', '♪ こんな 夏 だった', 0),
(521, 4, '夏', 'なつ', '夏天', '♪ こんな 夏 だった', 0),
(522, 4, 'だった', 'datta', '是 (過去式)', '♪ こんな 夏 だった', 0),
(523, 4, '砂', 'すな', '沙子', '♪ 砂 まじり の 風 が 吹いてた', 0),
(524, 4, 'まじり', 'まじり', '混雜', '♪ 砂 まじり の 風 が 吹いてた', 0),
(525, 4, '風', 'かぜ', '風', '♪ 砂 まじり の 風 が 吹いてた', 0),
(526, 4, '吹いてた', 'ふいてた', '吹著', '♪ 砂 まじり の 風 が 吹いてた', 0),
(527, 4, 'グランド', 'gurando', '操場、運動場 (Ground)', '♪ グランド の 真上 の 空', 0),
(528, 4, '真上', 'まうえ', '正上方', '♪ グランド の 真上 の 空', 0),
(529, 4, '夕日', 'ゆうひ', '夕陽', '♪ 夕日 が まぶしくて', 0),
(530, 4, 'まぶしくて', 'まぶしくて', '刺眼、耀眼', '♪ 夕日 が まぶしくて', 0),
(531, 4, 'どこ', 'doko', '哪裡', '♪ どこ まで 頑張れば いいん だ', 0),
(532, 4, '頑張れば', 'がんばれば', '努力的話', '♪ どこ まで 頑張れば いいん だ', 0),
(533, 4, 'いいん', 'iin', '就好', '♪ どこ まで 頑張れば いいん だ', 0),
(534, 4, 'だ', 'da', '是 (斷定助動詞)', '♪ どこ まで 頑張れば いいん だ', 0),
(535, 4, 'ぎゅっと', 'gyutto', '緊緊地', '♪ ぎゅっと 唇 を 噛みしめた', 0),
(536, 4, '唇', 'くちびる', '嘴唇', '♪ ぎゅっと 唇 を 噛みしめた', 0),
(537, 4, '噛みしめた', 'かみしめた', '咬緊', '♪ ぎゅっと 唇 を 噛みしめた', 0),
(538, 4, 'そんな', 'sonna', '那樣的', '♪ そんな 時 同じ 目 を した', 0),
(539, 4, '同じ', 'おなじ', '相同、一樣', '♪ そんな 時 同じ 目 を した', 0),
(540, 4, '目', 'め', '眼睛、目光', '♪ そんな 時 同じ 目 を した', 0),
(541, 4, 'した', 'shita', '做了 / 的', '♪ そんな 時 同じ 目 を した', 0),
(542, 4, '出会ったん', 'であったん', '相遇了', '♪ 君 に 出会ったん だ', 0),
(543, 4, 'そう', 'sou', '是的、那樣', '♪ そう 簡単 じゃ ない から こそ', 0),
(544, 4, '簡単', 'かんたん', '簡單', '♪ そう 簡単 じゃ ない から こそ', 0),
(545, 4, 'こそ', 'koso', '正因為...', '♪ そう 簡単 じゃ ない から こそ', 0),
(546, 4, '夢', 'ゆめ', '夢想', '♪ 夢 は こんな に 輝くん だ と', 0),
(547, 4, '輝くん', 'かがやくん', '閃耀', '♪ 夢 は こんな に 輝くん だ と', 0),
(548, 4, '言葉', 'ことば', '話語', '♪ あの 日 の 君 の 言葉', 0),
(549, 4, '今', 'いま', '現在', '♪ 今 でも 胸 に 抱きしめてる よ', 0),
(550, 4, '抱きしめてる', 'だきしめてる', '緊抱著、牢記著', '♪ 今 でも 胸 に 抱きしめてる よ', 0),
(551, 4, 'のばして', 'のばして', '伸出', '♪ あの 空 へ のばして あの 空 へ', 0),
(552, 4, 'いつも', 'itsumo', '總是', '♪ いつも どうしても 素直 に なれず に', 0),
(553, 4, 'どうしても', 'doushitemo', '無論如何', '♪ いつも どうしても 素直 に なれず に', 0),
(554, 4, '素直', 'すなお', '坦率、老實', '♪ いつも どうしても 素直 に なれず に', 0),
(555, 4, 'なれず', 'なれず', '無法變成', '♪ いつも どうしても 素直 に なれず に', 0),
(556, 4, '自信', 'じしん', '自信', '♪ 自信 なんて まるで 持てず に', 0),
(557, 4, 'まるで', 'marude', '宛如、完全', '♪ 自信 なんて まるで 持てず に', 0),
(558, 4, '持てず', 'もてず', '無法擁有', '♪ 自信 なんて まるで 持てず に', 1),
(559, 4, '校舎', 'こうしゃ', '校舍', '♪ 校舎 の 裏側 人目 を 気 に して', 0),
(560, 4, '裏側', 'うらがわ', '背面、後方', '♪ 校舎 の 裏側 人目 を 気 に して', 0),
(561, 4, '人目', 'ひとめ', '他人目光', '♪ 校舎 の 裏側 人目 を 気 に して', 0),
(562, 4, '気', 'き', '在意 (気にして)', '♪ 校舎 の 裏側 人目 を 気 に して', 0),
(563, 4, 'して', 'shite', '做', '♪ 校舎 の 裏側 人目 を 気 に して', 1),
(564, 4, '歩いてた', 'あるいてた', '走著', '♪ 気 に して 歩いてた', 0),
(565, 4, '誰', 'だれ', '誰', '♪ 誰 か と ぶつかりあう こと を', 0),
(566, 4, 'ぶつかりあう', 'ぶつかりあう', '互相碰撞', '♪ 誰 か と ぶつかりあう こと を', 0),
(567, 4, '心', 'こころ', '心', '♪ 心 の どこ か で 遠ざけた', 0),
(568, 4, '遠ざけた', 'とおざけた', '遠離', '♪ 心 の どこ か で 遠ざけた', 0),
(569, 4, 'それ', 'sore', '那個', '♪ それ は 本当 の 自分 を 見せる の が 怖い だけ だったん だ と', 0),
(570, 4, '本当', 'ほんとう', '真正、真實', '♪ それ は 本当 の 自分 を 見せる の が 怖い だけ だったん だ と', 0),
(571, 4, '自分', 'じぶん', '自己', '♪ それ は 本当 の 自分 を 見せる の が 怖い だけ だったん だ と', 0),
(572, 4, '見せる', 'みせる', '給...看、展現', '♪ それ は 本当 の 自分 を 見せる の が 怖い だけ だったん だ と', 0),
(573, 4, '怖い', 'こわい', '害怕', '♪ それ は 本当 の 自分 を 見せる の が 怖い だけ だったん だ と', 0),
(574, 4, 'だけ', 'dake', '只有、只是', '♪ それ は 本当 の 自分 を 見せる の が 怖い だけ だったん だ と', 0),
(575, 4, '教えて', 'おしえて', '教導、告訴', '♪ 教えて くれた の は', 0),
(576, 4, 'くれた', 'kureta', '給予我', '♪ 教えて くれた の は', 0),
(577, 4, '過ごした', 'すごした', '度過的', '♪ 君 と 過ごした 今日 まで の 日々', 0),
(578, 4, '今日', 'きょう', '今天', '♪ 君 と 過ごした 今日 まで の 日々', 0),
(579, 4, '日々', 'ひび', '每一天', '♪ 君 と 過ごした 今日 まで の 日々', 0),
(580, 4, '初めて', 'はじめて', '第一次', '♪ そう 初めて 口 に 出来た', 0),
(581, 4, '口', 'くち', '嘴巴、說出口', '♪ そう 初めて 口 に 出来た', 0),
(582, 4, '出来た', 'できた', '做到了', '♪ そう 初めて 口 に 出来た', 0),
(583, 4, '泣きたい', 'なきたい', '想哭', '♪ 泣きたい くらい の 本当 の 夢 を', 0),
(584, 4, 'くらい', 'kurai', '大約、程度', '♪ 泣きたい くらい の 本当 の 夢 を', 0),
(585, 4, 'ひとつ', 'hitotsu', '一個', '♪ あと ひとつ の 坂道 を', 0),
(586, 4, '坂道', 'さかみち', '坡道', '♪ あと ひとつ の 坂道 を', 0),
(587, 4, '夜', 'よる', '夜晚', '♪ ひとつ だけ の 夜 を 越えられた なら', 0),
(588, 4, '越えられた', 'こえられた', '能跨越', '♪ ひとつ だけ の 夜 を 越えられた なら', 0),
(589, 4, '笑える', 'わらえる', '能笑出來', '♪ 笑える 日 が 来る って', 0),
(590, 4, 'あつく', 'atsuku', '熱烈地', '♪ あつく なって も 無駄 なんて 言葉 聞き飽きた よ', 0),
(591, 4, 'なって', 'natte', '變成', '♪ あつく なって も 無駄 なんて 言葉 聞き飽きた よ', 0),
(592, 4, '無駄', 'むだ', '白費、徒勞', '♪ あつく なって も 無駄 なんて 言葉 聞き飽きた よ', 0),
(593, 4, '聞き飽きた', 'ききあきた', '聽膩了', '♪ あつく なって も 無駄 なんて 言葉 聞き飽きた よ', 0),
(594, 4, 'もしも', 'moshimo', '如果', '♪ もしも そう だ としても', 0),
(595, 4, '抑えきれない', 'おさえきれない', '無法壓抑', '♪ 抑えきれない この 気持ち を', 0),
(596, 4, '気持ち', 'きもち', '心情、情緒', '♪ 抑えきれない この 気持ち を', 0),
(597, 4, '希望', 'きぼう', '希望', '♪ 希望 と 呼ぶ なら いったい', 0),
(598, 4, '呼ぶ', 'よぶ', '呼喚', '♪ 希望 と 呼ぶ なら いったい', 0),
(599, 4, 'いったい', 'ittai', '到底、究竟', '♪ 希望 と 呼ぶ なら いったい', 0),
(600, 4, '止められる', 'とめられる', '能阻止', '♪ 誰 が 止められる と 言う の だろう', 0),
(601, 4, 'だろう', 'darou', '吧 (推測)', '♪ 誰 が 止められる と 言う の だろう', 0),
(602, 4, '明日', 'あした / asu', '明天', '♪ 明日 を 変える その 時 を 見たん だ', 0),
(603, 4, '変える', 'かえる', '改變', '♪ 明日 を 変える その 時 を 見たん だ', 0),
(604, 4, '見たん', 'みたん', '看到了', '♪ 明日 を 変える その 時 を 見たん だ', 0),
(605, 4, 'なくしかけた', 'なくしかけた', '差點失去', '♪ なくしかけた 光 君 が 思い出させて くれた', 0),
(606, 4, '光', 'ひかり', '光芒', '♪ なくしかけた 光 君 が 思い出させて くれた', 0),
(607, 4, '思い出させて', 'おもいださせて', '讓我想起', '♪ なくしかけた 光 君 が 思い出させて くれた', 0),
(608, 4, '景色', 'けしき', '景色', '♪ あの 日 の 景色 忘れない', 0),
(609, 4, '忘れない', 'わすれない', '不忘記', '♪ あの 日 の 景色 忘れない', 0),
(610, 3, 'coconut', '/ˈkoʊkəˌnʌt/', '椰子', '♪ The coconut nut is a giant nut', 0),
(611, 3, 'nut', '/nʌt/', '堅果 / 果實', '♪ The coconut nut is a giant nut', 0),
(612, 3, 'giant', '/ˈdʒaɪənt/', '巨大的', '♪ The coconut nut is a giant nut', 0),
(613, 3, 'eat', '/iːt/', '吃', '♪ If you eat too much', 0),
(614, 3, 'too', '/tuː/', '太... / 也', '♪ If you eat too much', 0),
(615, 3, 'much', '/mʌtʃ/', '許多 / 大量', '♪ If you eat too much', 0),
(616, 3, 'youll', '/juːl/', '你將會 (you will)', '♪ youll get very fat', 0),
(617, 3, 'get', '/ɡet/', '變得 / 得到', '♪ youll get very fat', 0),
(618, 3, 'very', '/ˈveri/', '非常', '♪ youll get very fat', 0),
(619, 3, 'fat', '/fæt/', '胖的 / 脂肪', '♪ youll get very fat', 0),
(620, 3, 'Now', '/naʊ/', '現在', '♪ Now, the coconut nut is a big, big nut', 0),
(621, 3, 'big', '/bɪɡ/', '大的', '♪ Now, the coconut nut is a big, big nut', 0),
(622, 3, 'But', '/bʌt/', '但是', '♪ But this delicious nut is not a nut', 0),
(623, 3, 'this', '/ðɪs/', '這個', '♪ But this delicious nut is not a nut', 0),
(624, 3, 'delicious', '/dɪˈlɪʃəs/', '美味的', '♪ But this delicious nut is not a nut', 0),
(625, 3, 'is', '/ɪz/', '是', '♪ But this delicious nut is not a nut', 0),
(626, 3, 'not', '/nɑːt/', '不是', '♪ But this delicious nut is not a nut', 0),
(627, 3, 'Its', '/ɪts/', '它是 (It is)', '♪ Its the coco fruit', 0),
(628, 3, 'coco', '/ˈkoʊkoʊ/', '椰子 (簡稱)', '♪ Its the coco fruit', 0),
(629, 3, 'fruit', '/fruːt/', '水果 / 果實', '♪ Its the coco fruit', 0),
(630, 3, 'Of', '/ʌv/', '...的 (屬於)', '♪ Of the coco tree', 0),
(631, 3, 'tree', '/triː/', '樹', '♪ Of the coco tree', 0),
(632, 3, 'From', '/frʌm/', '來自', '♪ From the coco palm family', 0),
(633, 3, 'palm', '/pɑːm/', '棕櫚樹', '♪ From the coco palm family', 0),
(634, 3, 'family', '/ˈfæməli/', '家族 / 科(生物)', '♪ From the coco palm family', 0),
(635, 3, 'There', '/ðer/', '那裡 / 有...', '♪ There are so many uses', 0),
(636, 3, 'are', '/ɑːr/', '是 / 有', '♪ There are so many uses', 0),
(637, 3, 'so', '/soʊ/', '如此', '♪ There are so many uses', 0),
(638, 3, 'many', '/ˈmeni/', '許多', '♪ There are so many uses', 0),
(639, 3, 'uses', '/ˈjuːsɪz/', '用途', '♪ There are so many uses of the coconut tree', 0),
(640, 3, 'You', '/juː/', '你', '♪ You can build a big house', 0),
(641, 3, 'can', '/kæn/', '能夠', '♪ You can build a big house', 0),
(642, 3, 'build', '/bɪld/', '建造', '♪ You can build a big house', 0),
(643, 3, 'house', '/haʊs/', '房子', '♪ You can build a big house', 0),
(644, 3, 'for', '/fɔːr/', '為了 / 給', '♪ for the family', 0),
(645, 3, 'All', '/ɔːl/', '全部 / 所有', '♪ All you need is to find', 0),
(646, 3, 'need', '/niːd/', '需要', '♪ All you need is to find', 0),
(647, 3, 'find', '/faɪnd/', '尋找', '♪ All you need is to find', 0),
(648, 3, 'man', '/mæn/', '男人 / 人', '♪ find a coconut man', 0),
(649, 3, 'If', '/ɪf/', '如果', '♪ If he cuts the tree', 0),
(650, 3, 'he', '/hiː/', '他', '♪ If he cuts the tree', 0),
(651, 3, 'cuts', '/kʌts/', '砍 / 切', '♪ If he cuts the tree', 0),
(652, 3, 'gets', '/ɡets/', '得到', '♪ he gets the fruit free', 0),
(653, 3, 'free', '/friː/', '免費的 / 自由的', '♪ he gets the fruit free', 0),
(654, 3, 'bark', '/bɑːrk/', '樹皮', '♪ The coconut bark for the kitchen floor', 0),
(655, 3, 'kitchen', '/ˈkɪtʃɪn/', '廚房', '♪ The coconut bark for the kitchen floor', 0),
(656, 3, 'floor', '/flɔːr/', '地板', '♪ The coconut bark for the kitchen floor', 0),
(657, 3, 'save', '/seɪv/', '保存 / 留下', '♪ If you save some of it', 0),
(658, 3, 'some', '/sʌm/', '一些', '♪ If you save some of it', 0),
(659, 3, 'it', '/ɪt/', '它', '♪ If you save some of it', 0),
(660, 3, 'door', '/dɔːr/', '門', '♪ you can build a door', 0),
(661, 3, 'trunk', '/trʌŋk/', '樹幹', '♪ Now, the coconut trunk', 0),
(662, 3, 'do', '/duː/', '做 / (助動詞)', '♪ do not throw this junk', 0),
(663, 3, 'throw', '/θroʊ/', '丟棄', '♪ do not throw this junk', 0),
(664, 3, 'junk', '/dʒʌŋk/', '垃圾 / 廢棄物', '♪ do not throw this junk', 0),
(665, 3, 'have', '/hæv/', '擁有', '♪ youll have a second floor', 0),
(666, 3, 'second', '/ˈsekənd/', '第二的', '♪ youll have a second floor', 0),
(667, 3, 'wood', '/wʊd/', '木材', '♪ The coconut wood is very good', 0),
(668, 3, 'good', '/ɡʊd/', '好的', '♪ The coconut wood is very good', 0),
(669, 3, 'stand', '/stænd/', '支撐 / 屹立', '♪ It can stand 20 years', 0),
(670, 3, 'years', '/jɪrz/', '年 (複數)', '♪ It can stand 20 years', 0),
(671, 3, 'pray', '/preɪ/', '祈禱', '♪ if you pray it would', 0),
(672, 3, 'would', '/wʊd/', '將會', '♪ if you pray it would', 0),
(673, 3, 'root', '/ruːt/', '根部', '♪ Now, the coconut root', 0),
(674, 3, 'tell', '/tel/', '告訴', '♪ to tell you the truth', 0),
(675, 3, 'truth', '/truːθ/', '真相 / 實話', '♪ to tell you the truth', 0),
(676, 3, 'use', '/juːz/', '使用', '♪ or use it as firewood', 0),
(677, 3, 'as', '/æz/', '作為', '♪ or use it as firewood', 0),
(678, 3, 'firewood', '/ˈfaɪərwʊd/', '柴火', '♪ or use it as firewood', 0),
(679, 3, 'leaves', '/liːvz/', '葉子 (leaf的複數)', '♪ The coconut leaves', 0),
(680, 3, 'shade', '/ʃeɪd/', '陰影 / 乘涼處', '♪ good shade it gives', 0),
(681, 3, 'gives', '/ɡɪvz/', '給予', '♪ good shade it gives', 0),
(682, 3, 'roof', '/ruːf/', '屋頂', '♪ For the roof', 0),
(683, 3, 'walls', '/wɔːlz/', '牆壁 (複數)', '♪ for the walls up against the eaves', 0),
(684, 3, 'up', '/ʌp/', '向上', '♪ up against the eaves', 0),
(685, 3, 'against', '/əˈɡenst/', '靠著 / 對抗', '♪ up against the eaves', 0),
(686, 3, 'eaves', '/iːvz/', '屋簷', '♪ up against the eaves', 0),
(687, 3, 'say', '/seɪ/', '說', '♪ say my relatives', 0),
(688, 3, 'my', '/maɪ/', '我的', '♪ say my relatives', 0),
(689, 3, 'relatives', '/ˈrelətɪvz/', '親戚', '♪ say my relatives', 0),
(690, 3, 'Make', '/meɪk/', '製造 / 成為', '♪ Make good cannonballs', 0),
(691, 3, 'cannonballs', '/ˈkænənˌbɔlz/', '砲彈', '♪ Make good cannonballs', 0),
(692, 3, 'thieves', '/θiːvz/', '小偷 (thief的複數)', '♪ up against the thieves', 0),

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `artists`
--
ALTER TABLE `artists`
  ADD PRIMARY KEY (`id`),
  ADD KEY `country_id` (`country_id`);

--
-- 資料表索引 `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `songs`
--
ALTER TABLE `songs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `artist_id` (`artist_id`);

--
-- 資料表索引 `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- 資料表索引 `vocabularies`
--
ALTER TABLE `vocabularies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `song_id` (`song_id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `artists`
--
ALTER TABLE `artists`

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `countries`
--
ALTER TABLE `countries`

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `songs`
--
ALTER TABLE `songs`

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `vocabularies`
--
ALTER TABLE `vocabularies`

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `artists`
--
ALTER TABLE `artists`
  ADD CONSTRAINT `artists_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`) ON DELETE CASCADE;

--
-- 資料表的限制式 `songs`
--
ALTER TABLE `songs`
  ADD CONSTRAINT `songs_ibfk_1` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`) ON DELETE CASCADE;

--
-- 資料表的限制式 `vocabularies`
--
ALTER TABLE `vocabularies`
  ADD CONSTRAINT `vocabularies_ibfk_1` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
