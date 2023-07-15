package com.example.rolcampeon.data

import com.example.rolcampeon.models.Champ
import com.example.rolcampeon.models.Role

class BDChamps {
    companion object {
        var champs = mutableListOf(
            Champ(
                "Ahri",
                "Ahri es una espíritu zorro vastaya con la capacidad de manipular la esencia mágica a su alrededor. Es una campeona muy popular por su agilidad y habilidades de control de masas.",
                2011,
                listOf(
                    Role(
                        "Mid",
                        "El rol de Mid Lane se destaca por tener un control importante del mapa y realizar emboscadas a los oponentes en la línea del medio.",
                        "mid_icon.png"
                    )
                )
            ),
            Champ(
                "Lee Sin",
                "Lee Sin es un monje ciego que utiliza su habilidad marcial y su percepción sónica para enfrentarse a sus enemigos. Es uno de los campeones más populares y versátiles del juego.",
                2011,
                listOf(
                    Role(
                        "Jungle",
                        "El rol de Jungla se encarga de neutralizar monstruos en la selva y brindar apoyo a las diferentes líneas del mapa.",
                        "jungle_icon.png"
                    )
                )
            ),
            Champ(
                "Ashe",
                "Ashe es una arquera de hielo que utiliza su arco para disparar flechas congelantes a sus enemigos. Es una campeona de daño a distancia y control de masas.",
                2009,
                listOf(
                    Role(
                        "ADC",
                        "El rol de ADC (Ataque a distancia) se encarga de infligir daño constante a los enemigos desde la línea inferior del mapa.",
                        "adc_icon.png"
                    )
                )
            ),
            Champ(
                "Garen",
                "Garen es un guerrero valiente y resistente que utiliza su espada y su armadura para enfrentarse a sus enemigos. Es un campeón de combate cuerpo a cuerpo y resistencia.",
                2010,
                listOf(
                    Role(
                        "Top",
                        "El rol de Top Lane se destaca por ser una línea de combate cuerpo a cuerpo y tener un impacto importante en las peleas de equipo.",
                        "top_icon.png"
                    )
                )
            ),
            Champ(
                "Lux",
                "Lux es una maga de luz que utiliza sus habilidades mágicas para dañar y controlar a sus enemigos. Es una campeona de daño a distancia y utilidad.",
                2010,
                listOf(
                    Role(
                        "Support",
                        "El rol de Support se encarga de proteger y apoyar a su equipo, brindando utilidad y control de masas en las peleas de equipo.",
                        "support_icon.png"
                    )
                )
            ),
            Champ(
                "Yasuo",
                "Yasuo es un espadachín ágil y letal que utiliza su espada y su habilidad para controlar el viento. Es un campeón de alta movilidad y daño explosivo.",
                2013,
                listOf(
                    Role(
                        "Mid",
                        "El rol de Mid Lane se destaca por tener un control importante del mapa y realizar emboscadas a los oponentes en la línea del medio.",
                        "mid_icon.png"
                    )
                )
            ),
            Champ(
                "Ezreal",
                "Ezreal es un aventurero y explorador que utiliza su arco mágico para disparar proyectiles místicos a sus enemigos. Es un campeón de daño a distancia y movilidad.",
                2010,
                listOf(
                    Role(
                        "ADC",
                        "El rol de ADC (Ataque a distancia) se encarga de infligir daño constante a los enemigos desde la línea inferior del mapa.",
                        "adc_icon.png"
                    )
                )
            ),
            Champ(
                "Zed",
                "Zed es un asesino sombrío que utiliza sus sombras y su habilidad con las espadas para eliminar a sus enemigos. Es un campeón de alta movilidad y daño explosivo.",
                2012,
                listOf(
                    Role(
                        "Mid",
                        "El rol de Mid Lane se destaca por tener un control importante del mapa y realizar emboscadas a los oponentes en la línea del medio.",
                        "mid_icon.png"
                    )
                )
            ),
            Champ(
                "Thresh",
                "Thresh es un guardián de las almas que utiliza su cadena y su linterna para atrapar y controlar a sus enemigos. Es un campeón de utilidad y control de masas.",
                2013,
                listOf(
                    Role(
                        "Support",
                        "El rol de Support se encarga de proteger y apoyar a su equipo, brindando utilidad y control de masas en las peleas de equipo.",
                        "support_icon.png"
                    )
                )
            ),
            Champ(
                "Darius",
                "Darius es un guerrero poderoso y temible que utiliza su hacha y su fuerza bruta para aplastar a sus enemigos. Es un campeón de combate cuerpo a cuerpo y daño explosivo.",
                2012,
                listOf(
                    Role(
                        "Top",
                        "El rol de Top Lane se destaca por ser una línea de combate cuerpo a cuerpo y tener un impacto importante en las peleas de equipo.",
                        "top_icon.png"
                    )
                )
            )
        )
        fun filtrarCampeonesPorRol(nombreRol: String): List<Champ> {
            return champs.filter { champ ->
                champ.roles.any { role ->
                    role.name.equals(nombreRol, ignoreCase = true)
                }
            }
        }
        fun eliminarChamp(nombreChamp: String) {
            champs.removeAll { it.name == nombreChamp }
        }
        fun actualizarChamp(nombre: String, nuevoChamp: Champ) {
            var champExistente = champs.find { it.name == nombre }
            champExistente?.let {
                it.name = nuevoChamp.name
                it.description = nuevoChamp.description
                it.releaseYear = nuevoChamp.releaseYear
                it.roles = nuevoChamp.roles
            }
        }


    }
}
