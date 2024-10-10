package com.gift.buzzhub

import android.text.Html.ImageGetter

class Events(val eventId:String="",
            var eventName:String="",
            var eventDetails:String="",
            var eventHostName:String="",
            var eventHostId:String="",
            var eventPrice:Double=0.0,
            var eventClicks:Int=0,
            var eventPurchases:Int=0,
            var eventCategory:String="",
            var eventCapacity:Long=0,
            var eventImage:Int=0) {
}


/*
package com.gift.buzzhub

class Categories(val categoryId:String="",
                var categoryName:String="",
                var categoryDetails:String="",
                val categoryHostName:String="",
                val categoryHostID:String="",
                var categoryPrice:Double=0.0,
                var categoryClicks:Int=0,
                var categoryPurchases:Int=0,){
}



* */