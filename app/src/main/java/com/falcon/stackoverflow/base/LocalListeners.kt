package com.falcon.stackoverflow.base

class LocalListeners {

    interface OnSuccessListener{
        fun onSuccess(b: Boolean)
        fun onFailed()
    }
}
