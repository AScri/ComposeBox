package com.ascri.composebox.data.source.remote

object NetworkContract {
    const val KEY_PROVIDER = "provider"
    const val KEY_TOKENS = "tokens"

    object Login {
        const val LOGOUT_ENDPOINT = "logout/"
        const val LOGIN_ENDPOINT = "register/"
        const val TOKEN_ENDPOINT = "token/"
        const val TOKEN_REFRESH_ENDPOINT = "token/refresh/"
        const val VERIFY_ENDPOINT = "verify/"
        const val SIGN_UP_ENDPOINT = "signup/"
        const val SIGN_UP_UPDATE_ENDPOINT = "signup/update/"
        const val SIGN_UP_COMPLETE_ENDPOINT = "signup/complete/"
        const val SIGN_UP_AVATAR_ENDPOINT = "signup/photo/"
    }

    object Clubs {
        const val CLUBS_ENDPOINT = "muc/clubs/"
        const val CREATE_CLUBS_ENDPOINT = "muc/clubs/"
        const val GET_CLUB_ENDPOINT = "muc/clubs/{id}/"
        const val CLUB_TABLES_ENDPOINT = "muc/clubs/{id}/tables"
        const val TABLES_PARTICIPANTS_ENDPOINT = "muc/clubs/{club_id}/tables/{table_id}/participants"
        const val PUT_CLUB_IMAGE_ENDPOINT = "muc/clubs/{id}/image/"
        const val GET_USER_CLUBS_ENDPOINT = "muc/clubs/{user_id}/list"
        const val PERFORMANCES_ENDPOINT = "muc/performances/"
        const val PERFORMANCES_BY_ID_ENDPOINT = "muc/performances/{id}/"
        const val PERFORMANCES_BY_CLUBID_ENDPOINT = "muc/performances/{club_id}/list"
        const val CLUB_MUSIC_IS_PLAYING_ENDPOINT = "music/{club_id}/is-playing/"
        const val CLUB_TO_FAVORITES = "muc/clubs/{club_id}/favorites/"
        const val GET_SUBSCRIBERS = "muc/clubs/{club_id}/favorite_people_list/"
        const val CLUBS_PARTICIPANTS = "muc/clubs/{id}/participants/"
    }

    object Rooms {
        const val ROOMS_ENDPOINT = "muc/rooms/"
        const val ROOM_PARTICIPANTS_ENDPOINT = "muc/rooms/{room_id}/participants"
    }

    object Users {
        const val ONLINE_USERS_ENDPOINT = "users/online/random/"
        const val MY_PROFILE_ENDPOINT = "me/profile/"
        const val USER_PROFILE_ENDPOINT = "users/jid/{jid}/"
        const val USER_DETAILED_PROFILE_ENDPOINT = "users/{userId}/"
        const val NOTIFICATION_ENDPOINT = "me/notifications/"
        const val NOTIFICATION_ENABLE_ENDPOINT = "me/notifications/enable/"
        const val MY_PHOTO_ENDPOINT = "me/photo/"
        const val MY_FOLLOWERS = "me/followers/"
        const val MY_FOLLOWINGS = "me/follows/"
        const val FOLLOW_USER = "users/{user_id}/follow/"
        const val USER_FOLLOWERS = "users/{user_id}/followers/"
        const val USER_FOLLOWINGS = "users/{user_id}/follows/"
        const val INVITES = "invites/"
        const val CONTACTS = "me/contacts/{device_id}/"
        const val REGISTERED_CONTACTS = "me/contacts/{device_id}/registered/"
        const val UNREGISTERED_CONTACTS = "me/contacts/{device_id}/unregistered/"
        const val INTERESTS = "interests/"
        const val SEARCH_USERS = "users/search/"
        const val USER_FAVORITE_CLUBS = "users/{user_id}/favorites/"
        const val COMPLAINT_ENDPOINT = "complaints/"
    }

    object Tables {
        const val CREATE_TABLE_ENDPOINT = "muc/clubs/{club_id}/tables/"
        const val PUT_TABLE_IMAGE_ENDPOINT = "muc/clubs/{club_id}/tables/{id}/image/"
        const val DELETE_TABLE_ENDPOINT = "muc/clubs/{club_id}/tables/{id}/"
        const val GET_TABLE_ENDPOINT = "muc/clubs/{club_id}/tables/{id}/"
        const val GET_TABLE_BY_ID_ENDPOINT = "muc/tables/{id}/"
    }

    object Smiles {
        const val ALL = "smiles/"
        const val BY_ID = "smiles/{smile_id}/"
    }

}