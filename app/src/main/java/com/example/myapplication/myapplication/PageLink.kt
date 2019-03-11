package com.example.myapplication.myapplication

import okhttp3.Response

class PageLinks{

    var DELIM_LINKS = ","

    var DELIM_LINK_PARAM = ";"
    var META_REL = "rel"
    var META_FIRST = "first"
    var META_LAST = "last"
    var META_NEXT = "next"
    var META_PREV = "prev"

    var first = ""
    var last = ""
    var next = ""
    var prev = ""

    fun pageLinks (response: Response) : String  {
        var linkHeader = response.header("Link")
        if (linkHeader != null) {

            var links = linkHeader.split(DELIM_LINKS)
            for (i in links) {
                var segments: List<String> = i.split(DELIM_LINK_PARAM)
                if (segments.size < 2)
                    continue
                var linkPart = segments[0].trim()
                if (!linkPart.startsWith("<") || !linkPart.endsWith(">"))
                    continue
                linkPart = linkPart.substring(1, linkPart.length - 1)

                for (i in segments) {
                    var rel = i.trim().split("=")
                    if ((rel.size < 2) || !META_REL.equals(rel[0]))
                        continue
                    var relValue = rel[1]
                    if (relValue.startsWith("\"") && relValue.endsWith("\""))
                        relValue = relValue.substring(1, relValue.length - 1)

                    if (META_FIRST.equals(relValue))
                    {
                        first = linkPart
                        print(first)}
                    else if (META_LAST.equals(relValue))
                    {
                        last = linkPart
                        print(last)}
                    else if (META_NEXT.equals(relValue))
                    {
                        next = linkPart
                        print(next)}
                    else if (META_PREV.equals(relValue))
                    { prev = linkPart
                        print(prev)}


                }

            }

        } else {
            next = response.header("next")!!
            last = response.header("last")!!

        }

        return last


      /*  fun getFirst() : String {
            return first
        }
        fun getLast() : String {
            return last
        }
        fun getNext() : String {
            return  next
        }

        fun getPrev()  : String {
            return first
        }*/


    }



}



