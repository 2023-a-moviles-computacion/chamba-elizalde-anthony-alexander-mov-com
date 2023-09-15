package com.example.rv_anthonychamba_googleapp.providers

import com.example.rv_anthonychamba_googleapp.models.GoogleNews

class GN_Provider {
    companion object{
        val googleNewsList = arrayListOf<GoogleNews>(
            GoogleNews(

                title = "Estos serán los clasificados al Mundial por la eliminatoria sudamericana",
                imageUrl = "https://example.com/image1.jpg",
                publishedAt = "Hace 3 días",

            ),                   GoogleNews(

            title = "                  'Tenía grandes esperanzas con la nueva serie de The Walking Dead, pero tras ver el avance...',\n",
            imageUrl = "https://example.com/image2.jpg",
            publishedAt = "Hace 1 hora",

        ),
            GoogleNews(
                title = "Filtrado el nuevo Chromecast con Google TV y mando a distancia ",
                imageUrl = "https://example.com/image2.jpg",
                publishedAt = "Hace 3 días",

                )



            )
    }
}