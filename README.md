# PickUpConnpassEvents
このアプリは、[connpass API](https://connpass.com/about/api/v2/)を叩いて、Android, iOS関連の勉強会やイベントの情報を取得してくるアプリです。  
取得してきた情報は、Events画面で一覧表示されます。また、気になるイベントをお気に入り登録することも可能です。  
お気に入り登録したイベントはFavorites画面で確認できます。

| Events画面 | Favorites画面 |
|-----------|-----------|
| <img src="https://github.com/user-attachments/assets/dee5ed80-386e-456f-9f46-47b58936a566" width=200> | <img src="https://github.com/user-attachments/assets/439daeed-1b88-4399-b9a0-31deef1fce20" width=200> |

また、iPhoneからもアプリを起動することができます。
| Events画面 | Favorites画面 |
|-----------|-----------|
| <img src="https://github.com/user-attachments/assets/dff58524-472e-4d34-a3fd-db373eb4ae50" width=200> | <img src="https://github.com/user-attachments/assets/3485602d-443d-40b3-af9c-3b28bc2c8bf8" width=200> |

また、ダークモードにも対応しています。

| Events画面 | Favorites画面 |
|-----------|-----------|
| <img src="https://github.com/user-attachments/assets/24537be5-9f94-4de2-8b7f-1421a3c3bbd1" width=200> | <img src="https://github.com/user-attachments/assets/8636495b-0ddf-4b48-b804-8b18ad24f71a" width=200> |


# 使用している主な技術
- ビジネスロジック：[KMP](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- UI：[CMP](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform.html)
- HTTPクライアントライブラリ：[Ktor Client](https://ktor.io/docs/client-create-multiplatform-application.html)
- DIライブラリ：[Koin](https://insert-koin.io/)
- DB：[Room](https://developer.android.com/kotlin/multiplatform/room?hl=ja)
- その他：[BuildKonfig](https://github.com/yshrsmz/BuildKonfig), [Connpass API](https://connpass.com/about/api/v2/), [kermit](https://kermit.touchlab.co/docs/)
