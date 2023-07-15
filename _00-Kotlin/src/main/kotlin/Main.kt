import java.util.Date

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    //Inmutable (No se reasignan)
    val inmutable: String = "Rommel"

    //Mutable (re asignar)
    var mutable: String = "Paul"
    mutable = "Rommel"

    //val > var

    //Duck typing
    val ejemploVariable = "Rommel Masabanda"
    val edadEjemplo: Int = 23
    ejemploVariable.trim()
    //ejemploVariable = edadEjemplo;

    //Variable primitiva
    val nombreProfesor: String = "Rommel Masabanda"
    val sueldo: Double = 1.2
    var estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true

    //Clases Java
    val fechaNacimiento: Date = Date()

    //No existe el switch, pero si el when
    val estadoCivilWhen = "C"
    when (estadoCivilWhen) {
        ("C") -> {
            println("Casado")
        }

        ("S") -> {
            println("Soltero")
        }

        else -> {
            println("no sabemos")
        }
    }

    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"
    calcularSueldo(10.00)
    calcularSueldo(10.00, 14.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = 14.00)
    calcularSueldo(bonoEspecial = 14.00, sueldo = 10.00, tasa = 12.00)


}

fun imprimirNombre(nombre: String): Unit {
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double,
    tasa: Double = 12.0,
    bonoEspecial: Double? = null,
): Double {
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }

}

abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int,
        dos: Int,
    ) {
        this.numeroUno = uno
        this.numeroDos = dos
        print("Inicializando")
    }
}
abstract class NumerosJavaCP(
    //uno: Int, Parametro sin modificador de acceso
    //private var uno: Int // No se puede acceder desde ninguna clase
    //protected var uno: Int // Se puede acceder desde la clase y sus subclases
    //internal var uno: Int // Se puede acceder desde el modulo
    protected val numeroUno: Int,
    protected val numeroDos: Int,
) {
    //El this es opcional.
    init {
        print("Inicializando clase Numeros JavaCP")
        this.numeroUno; this.numeroDos;
    }
}

class Suma(
    uno: Int,
    dos: Int,
) : NumerosJavaCP(uno, dos) {
    init {
        this.numeroUno;
        this.numeroDos;
    }

    constructor(
        uno: Int?,
        dos: Int,
        tres: Int,
    ) : this(
        if (uno == null ) 0 else uno, dos) {

        //this(uno, dos)
    }
    constructor(
        uno: Int,
        dos: Int?,
        tres: Int,
    ) : this(
        if (dos == null ) 0 else uno, uno) {

        //this(uno, dos)
    }
    constructor(
        uno: Int?,
        dos: Int?,
    ) : this(
        if (uno == null ) 0 else uno, if (dos == null ) 0 else dos

    )
    public fun sumar(): Int {
        //clase 30 de mayo.
        val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)
        println(arregloEstatico)
        val arregloDinamico:ArrayList<Int> = arrayListOf<Int>(1, 2, 3,4,5,6,7,8,9,10)
        println(arregloDinamico)
        arregloDinamico.add(11)
        arregloDinamico.add(12)
        println(arregloDinamico)
        val respuestaForEach: Unit = arregloDinamico
            .forEach { valorActual: Int ->
                println("Valor iteracion: ${valorActual}")
            }
        arregloDinamico.forEach{ println(it)}
        arregloEstatico.forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
        println(respuestaForEach)
        //operador map
        val respuestaMap: List<Double> = arregloDinamico
            .map { valorActual: Int ->
                return@map valorActual.toDouble() + 100.00
            }
        println(respuestaMap)
        val respuestaMapDos = arregloDinamico.map { it + 15 }
        //operador filter
        val respuestaFilter = arregloDinamico
            .filter { valorActual: Int ->
                val mayoresACinco: Boolean = valorActual > 5
                return@filter mayoresACinco
            }
        println(respuestaFilter)
        val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
        println(respuestaFilterDos)
        //operador any all
        val respuestaAny: Boolean = arregloDinamico
            .any { valorActual: Int ->
                return@any valorActual > 5
            }
        println(respuestaAny)
        val respuestaAll: Boolean = arregloDinamico
            .all { valorActual: Int ->
                return@all valorActual > 5
            }
        println(respuestaAll)
        val respuestaReduce = arregloDinamico
            .reduce { acumulado: Int, valorActual: Int ->
                return@reduce acumulado + valorActual
            }
        println(respuestaReduce)

        var total = numeroUno + numeroDos
        return total
    }

//this(uno, dos)
    }










