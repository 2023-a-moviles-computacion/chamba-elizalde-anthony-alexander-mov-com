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





