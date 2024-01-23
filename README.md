# 概要
・映画のタイトル名、公開年、制作した国をまとめたデータベースになります。
・映画情報の検索、登録、修正、削除が可能となっています。

# 使用技術
* バックエンド
  * Java 20.0.2
  * SpringBoot 3.1.5
  * MyBatis
* その他
  * MySQL 8.0.34
  * Docker 23.0.5
  * 自動テスト
  * CI (Checkstyle, Codecov, Discordへの通知, 自動テストを実行)

# アプリケーション機能一覧
機能	詳細	URL
検索	IDを指定して検索する	/movies/{id}
新規登録	ID及び付随する映画を新規登録する	/movies
修正	指定したIDの映画を修正する	/movies/{id}
削除	指定した映画を削除する	/movies/{id}
# DB定義
テーブル名：movie_database

|1|2|3|4|5|
|:-:|:-:|:-:|:-:|:-:|
|カラム名 |	データ型 | キー | NOT NULL | 備考|
|id |	VARCHAR(255) |	PRIMARY KEY | |映画ID,自動生成|
|name |	VARCHAR(255) | |	NOT NULL | 映画名|
|releaseYear |	VARCHAR(255) | | NOT NULL |	公開年|
|country |	VARCHAR(255) | | NOT NULL |	制作国|

# 機能の詳細と動作確認
**検索機能（Read）**
1.テーブルから映画を全件取得
2.ID検索し、特定の映画を取得
3.DBに存在しないIDを検索した場合の例外処理
**登録機能（Create）**
1.映画情報をテーブルに新規登録
2.テーブルに存在する映画名を再度登録する際の例外処理
**更新機能（Update）**
1.テーブルに存在する映画の情報更新
2.情報更新のためにテーブルにないIDを指定した場合の例外処理
**削除機能（Delete）**
1.IDを指定して該当する映画を削除
2.テーブルに存在しないIDを指定して削除する場合の例外処理
# テストコードの実装
下記のテストコードを実装しました。又、GitHubActionsにて自動化を行っています。

* 単体テスト
  * MovieMapperTest
  * MovieServiceTest
* 結合テスト
  * MovieRestApiIntegrationTest

# 今後について
Java言語でのAPI作成について基礎的なことを学ぶことができたと思うので、
このままJavaについての知識を深めつつ、React等のフロントエンド、
AWSなど知識を深めて、ゆくゆくはフルスタックなエンジニアを目指したいと考えています。
