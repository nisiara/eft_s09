
package eft_s09;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EFT_S09 {
  static Scanner respuestaUsuario = new Scanner(System.in);

  //VARIABLES USADAS POR SCANNER
  static int opcionUbicacion = 0;
  static int opcionCartelera = 0;
  static int opcionEvento = 0;
  static int opcionCodigo = (int) (Math.random() * 9001) + 1000;
  static int eleccionCodigoCliente = 0;

  //VARIABLES DE ESTADO
  static String eleccionSeccion = "";
  static String eleccionEvento = "";
  static String eleccionUbicacion = "";
  static int eleccionPrecio = 0;
  static double eleccionValorDescuento = 0;
  static String eleccionTipoDescuento = "";

  static List<String> listaEleccionSeccion  = new ArrayList<>();
  static List<String> listaEleccionEvento  = new ArrayList<>();
  static List<String> listaEleccionUbicacion  = new ArrayList<>();
  static List<Integer> listaEleccionPrecio  = new ArrayList<>();
  static List<Double> listaEleccionValorDescuento  = new ArrayList<>();
  static List<String> listaEleccionTipoDescuento  = new ArrayList<>();
  static List<Integer> listaEleccionCodigoCliente  = new ArrayList<>();

  final static double DESCUENTO_ESTUDIANTE = 0.05;
  final static double DESCUENTO_EDAD = 0.10;
  final static double DESCUENTO_CLUB = 0.15;

  //VARIABLES TIPO BANDERA
  static int entradasElegidas = 0;
  static int asientosDisponibles;

  //FUNCIONES
  static void cargarVariablesEstado(int respuestaUsuario, String[] secciones, int seccion, String evento, String[] ubicaciones, int[] precios){
    opcionUbicacion = respuestaUsuario;
    eleccionSeccion = secciones[seccion];
    eleccionEvento = evento;
    eleccionUbicacion = ubicaciones[opcionUbicacion];
    eleccionPrecio = precios[opcionUbicacion];
  }

  static void calcularDescuento(){
    System.out.println("+++++ Verifica si aplican descuentos +++++");
    int clubTeatroMoro;
    int esTerceraEdad;
    int esEstudiante;
    // Los descuentos no son acumulables. Si el cliente cumple uno de los criterios de unos de los
    //descuentos se aplica ese descuento sin preguntar por los restantes
    do{
      try {
        System.out.println("++++  [1]Sí [2]No - ¿El cliente es parte del \"Club Teatro Moro\"?  ++++");
        clubTeatroMoro = respuestaUsuario.nextInt();
        if(clubTeatroMoro != 1 && clubTeatroMoro != 2){
          System.out.println("\n+++ ERROR: Debes ingresar un número válido para poder continuar. +++" );
        }
      }
      catch (InputMismatchException error){
        System.out.println("+++ ERROR: Debes ingresar un número. +++" );
        respuestaUsuario.next();
        clubTeatroMoro = 0;
      }

    } while (clubTeatroMoro != 1 && clubTeatroMoro != 2);

    if(clubTeatroMoro == 1){
      eleccionValorDescuento = eleccionPrecio * DESCUENTO_CLUB;
      eleccionTipoDescuento = "15% Club Teatro Moro.";
      System.out.println("Tienes un descuento del " + eleccionTipoDescuento);
    }
    else{
      do {
        try {
          System.out.println("++++  [1]Sí [2]No - ¿El cliente es tercera edad?  ++++");
          esTerceraEdad = respuestaUsuario.nextInt();
          if(esTerceraEdad != 1 && esTerceraEdad != 2){
            System.out.println("\n+++ ERROR: Debes ingresar un número válido para poder continuar. +++" );
          }
        }
        catch (InputMismatchException error){
          System.out.println("+++ ERROR: Debes ingresar un número. +++" );
          respuestaUsuario.next();
          esTerceraEdad = 0;
        }

      } while(esTerceraEdad != 1 && esTerceraEdad != 2);

      if(esTerceraEdad == 1){
        eleccionValorDescuento = eleccionPrecio * DESCUENTO_EDAD;
        eleccionTipoDescuento = "10% Tercera Edad.";
        System.out.println("Tienes un descuento del " + eleccionTipoDescuento);
      }
      else {
        do {
          try{
            System.out.println("++++  [1]Sí [2]No - ¿El cliente es estudiante?  ++++");
            esEstudiante = respuestaUsuario.nextInt();
            if(esEstudiante != 1 && esEstudiante != 2){
              System.out.println("\n+++ ERROR: Debes ingresar un número válido para poder continuar. +++" );
            }
          }
          catch (InputMismatchException error){
            System.out.println("+++ ERROR: Debes ingresar un número. +++" );
            respuestaUsuario.next();
            esEstudiante = 0;
          }
        } while(esEstudiante != 1 && esEstudiante != 2);

        if(esEstudiante == 1){
          eleccionValorDescuento = eleccionPrecio * DESCUENTO_ESTUDIANTE;
          eleccionTipoDescuento = "5% Estudiante.";
          System.out.println("Tienes un descuento del " + eleccionTipoDescuento);
        }
        else{
          eleccionValorDescuento = 0;
          eleccionTipoDescuento = "Sin descuentos.";
          System.out.println(eleccionTipoDescuento);
        }
      }
    }
    //Agregar información elegida a las lista de descuento
    listaEleccionValorDescuento.add(eleccionValorDescuento);
    listaEleccionTipoDescuento.add(eleccionTipoDescuento);
  }

  static void cargarListas(){
    listaEleccionSeccion.add(eleccionSeccion);
    listaEleccionEvento.add(eleccionEvento);
    listaEleccionUbicacion.add(eleccionUbicacion);
    listaEleccionPrecio.add(eleccionPrecio);
  }

  static void mostrarResumen(){
    System.out.println("++++++  Resumen Entrada #" + entradasElegidas + "  ++++++");
    System.out.println("Código Entrada: " + eleccionCodigoCliente);
    System.out.println("Evento: " + eleccionEvento);
    System.out.println("Ubicación: " + eleccionUbicacion);
    System.out.println("Precio: $" + eleccionPrecio);
    System.out.println("Descuento: $" + eleccionValorDescuento);
  }

  static void generarCodigo(){
    System.out.println("------------------------------------------------------------------------------------");
    do {
      try {
        System.out.println("++++ Ingresa el código que aparece a continuación para generar la entrada:");
        System.out.println("*La persona con este código podrá usar la entrada al momento le llegar al evento");
        System.out.println(opcionCodigo);
        eleccionCodigoCliente = respuestaUsuario.nextInt();
        if (eleccionCodigoCliente != opcionCodigo) {
          System.out.println("\n+++ ERROR: Debes ingresar el mismo código que aparece en pantalla poder generar la entrada. +++");
        }
      }
      catch(InputMismatchException error){
        System.out.println("+++ ERROR: Debes ingresar dígitos. +++" );
        respuestaUsuario.next();
        eleccionCodigoCliente = 0;
      }

    } while(eleccionCodigoCliente != opcionCodigo);
    listaEleccionCodigoCliente.add(eleccionCodigoCliente);

    opcionCodigo = (int) (Math.random() * 9001) + 1000;
    System.out.println("++++ LA ENTRADA SE HA GENERADO DE FORMA EXITOSA  ++++");
  }

  static void mostrarTodosLosBoletos(){
    for (int i = 0; i < entradasElegidas; i++){
      System.out.println("++++++  Resumen Entrada #" + (i + 1) + "  ++++++");
      System.out.println("Código Cliente: " + listaEleccionCodigoCliente.get(i));
      System.out.println("Sección del Teatro Moro: " + listaEleccionSeccion.get(i));
      System.out.println("Evento: " + listaEleccionEvento.get(i));
      System.out.println("Ubicación: " + listaEleccionUbicacion.get(i));
      System.out.println("Precio: $" + listaEleccionPrecio.get(i));
      System.out.println("Descuento: $" + listaEleccionValorDescuento.get(i) + " - " + listaEleccionTipoDescuento.get(i) + "\n"); ;
    }
  }
  
  public static void main(String[] args) {
    
    //CONSTANTES PARA MOSTRAR INFORMACIÓN
    final String[] SECCIONES_TEATRO_MORO = {"Teatro", "Concierto", "Película", "Charla", "Standup"};

    final String EVENTO_TEATRO_MORO_OBRA = "La Pérgola de las Flores";
    final String EVENTO_TEATRO_MORO_CONCIERTO = "Dios Salve a la Reina. Tributo a Queen";
    final String EVENTO_TEATRO_MORO_PELICULA = "El Exorcista - Versión original 1973";
    final String EVENTO_TEATRO_MORO_CHARLA = "Aprender Java y no morir en el intento";
    final String EVENTO_TEATRO_MORO_STANDUP = "Tay Wando - Juan Pablo López";

    final String[] UBICACION_OBRA = {"VIP", "Platea Baja", "Platea Alta", "Palco"};
    int[] asientosDisponiblesObra = {0, 15, 20, 25, 30};
    final int[] PRECIOS_UBICACION_OBRA = {25000, 19000, 11000, 7200};

    final String[] UBICACION_CONCIERTO = {"VIP", "General", "Andes", "Tribuna", "Galeria"};
    final int[] asientosDisponiblesConcierto = {30, 30, 40, 40, 60};
    final int[] PRECIOS_UBICACION_CONCIERTO = {150000, 99000, 77000, 58000, 33000};

    final String[] UBICACION_PELICULA = {"Butaca Premium", "Butaca Gold", "Butaca"};
    final int[] asientosDisponiblesPelicula = {30, 30, 30};
    final int[] PRECIOS_UBICACION_PELICULA = {33000, 27000, 13400};

    final String[] UBICACION_CHARLA = {"General"};
    final int[] asientosDisponiblesCharla = {100};
    final int[] PRECIOS_UBICACION_CHARLA = {45500};

    final String[] UBICACION_STANDUP = {"Primer Piso", "Segundo Piso"};
    final int[] asientosDisponiblesStandUp = {40, 60};
    final int[] PRECIOS_UBICACION_STANDUP = {22500, 18000};


    //MENU PRINCIPAL
    int opcionMenu = 0;
    while(opcionMenu != 6){
      do{
        try{
          System.out.println("\n++++++++    MENÚ PRINCIPAL GESTIÓN TEATRO MORO    ++++++++");
          System.out.println("[1]VER DESCUENTOS");
          System.out.println("[2]VER CARTELERA");
          System.out.println("[3]REVISAR EVENTOS ELEGIDOS y SUS DETALLES");
          System.out.println("[4]EDITA EL CÓDIGO DE LA ENTRADA ");
          System.out.println("[5]ELIMINAR BOLETO ");
          System.out.println("[6]COMPRAR BOLETOS / SALIR DE LA APLICACIÓN ");
          System.out.println("++++ Ingresa el número de la acción que deseas realizar: ");

          opcionMenu = respuestaUsuario.nextInt();

          if(opcionMenu < 1 || opcionMenu > 6){
            System.out.println("\n+++ ERROR: Debes ingresar un número válido para poder continuar. +++" );
          }
        }
        catch (InputMismatchException error){
          System.out.println("+++ ERROR: Debes ingresar un número. +++" );
          respuestaUsuario.next();
          opcionMenu = 0;
        }
      } while(opcionMenu < 1 || opcionMenu > 6);


      switch(opcionMenu){
        //MENU PRINCIPAL - OPCION DESCUENTOS
        case 1:
          System.out.println("\n ++++++++++++++++  DESCUENTOS  +++++++++++++++");
          System.out.println("5% Descuento estudiantes");
          System.out.println("10% Descuento Tercera Edad");
          System.out.println("15% Descuento Club Teatro Moro");
          System.out.println("* Los descuentos se aplicarán al momento de elegir la ubicación. Estos no son acumulables *");
          break;

        //MENU PRINCIPAL - OPCIÓN CARTELERA
        case 2:
          do{
            try{
              System.out.println("\n ++++++++++++++++  CARTELERA  +++++++++++++++");
              System.out.println( "++  [1]TEATRO           ++ " + EVENTO_TEATRO_MORO_OBRA);
              System.out.println( "++  [2]CONCIERTO        ++ " + EVENTO_TEATRO_MORO_CONCIERTO);
              System.out.println( "++  [3]PELICULA         ++ " + EVENTO_TEATRO_MORO_PELICULA);
              System.out.println( "++  [4]CHARLA           ++ " + EVENTO_TEATRO_MORO_CHARLA);
              System.out.println( "++  [5]STAND UP COMEDY  ++ " + EVENTO_TEATRO_MORO_STANDUP);
              System.out.println("Ingresa el número del evento que quieres ver: ");
              opcionCartelera = respuestaUsuario.nextInt();

              if(opcionCartelera < 1 || opcionCartelera > 5){
                System.out.println("\n+++ ERROR: Debes ingresar un número válido para poder continuar. +++" );
              }
            }
            catch(InputMismatchException error){
              System.out.println("+++ ERROR: Debes ingresar un número. +++" );
              respuestaUsuario.next();
              opcionCartelera = 0;
            }

            //SUBMENU - OPCIÓN CARTELERA

            switch(opcionCartelera){
              //CARTELERA OBRA DE TEATRO
              case 1:

                do {
                  try{
                    System.out.println("\n ++++++++++  EVENTO: " + EVENTO_TEATRO_MORO_OBRA.toUpperCase() + "  ++++++++++");
                    System.out.println("[1]" +  UBICACION_OBRA[0] + " - Precio $" + PRECIOS_UBICACION_OBRA[0] + " - Asientos Disponibles " + asientosDisponiblesObra[0]);
                    System.out.println("[2]" +  UBICACION_OBRA[1] + " - Precio $" + PRECIOS_UBICACION_OBRA[1] + " - Asientos Disponibles " + asientosDisponiblesObra[1]);
                    System.out.println("[3]" +  UBICACION_OBRA[2] + " - Precio $" + PRECIOS_UBICACION_OBRA[2] + " - Asientos Disponibles " + asientosDisponiblesObra[2]);
                    System.out.println("[4]" +  UBICACION_OBRA[3] + " - Precio $" + PRECIOS_UBICACION_OBRA[3] + " - Asientos Disponibles " + asientosDisponiblesObra[3]);
                    System.out.println("++++ Elige la ubicación para la obra de teatro: " );
                    opcionEvento = respuestaUsuario.nextInt();

                    if(opcionEvento < 1 || opcionEvento > 4){
                      System.out.println("\n+++ ERROR: Debes ingresar un número válido para poder continuar. +++" );
                    }
                  }
                  catch(InputMismatchException error){
                    System.out.println("+++ ERROR: Debes ingresar un número. +++" );
                    respuestaUsuario.next();
                    opcionEvento = 0;
                  }
                } while (opcionEvento < 1 || opcionEvento > 4);

                //Validamos que la ubicación tenga asientos para descontarlo de la ubicación elegida
                asientosDisponibles = asientosDisponiblesObra[opcionEvento - 1];
                if(asientosDisponibles > 0){
                  asientosDisponiblesObra[opcionEvento - 1] = asientosDisponibles - 1;
                  cargarVariablesEstado(opcionEvento - 1, SECCIONES_TEATRO_MORO, 0, EVENTO_TEATRO_MORO_OBRA, UBICACION_OBRA, PRECIOS_UBICACION_OBRA);
                  System.out.println("++++  Seleccionaste la ubicación: " + eleccionUbicacion + " - Precio: $" + eleccionPrecio + "  ++++");
                  cargarListas();
                  calcularDescuento();

                  //Aumentar el contador de entradas elegidas
                  entradasElegidas++;
                  generarCodigo();
                  mostrarResumen();
                }
                else {
                  System.out.println("++++  No hay asientos disponibles para esta ubicación  ++++");
                }
                break;

              //CARTELERA CONCIERTO
              case 2:
                do {
                  try{
                    System.out.println("\n ++++++++++  CONCIERTO: " + EVENTO_TEATRO_MORO_CONCIERTO.toUpperCase() + "  ++++++++++");
                    System.out.println("[1]" +  UBICACION_CONCIERTO[0] + " - Precio $" + PRECIOS_UBICACION_CONCIERTO[0] + " - Asientos Disponibles " + asientosDisponiblesConcierto[0]);
                    System.out.println("[2]" +  UBICACION_CONCIERTO[1] + " - Precio $" + PRECIOS_UBICACION_CONCIERTO[1] + " - Asientos Disponibles " + asientosDisponiblesConcierto[1]);
                    System.out.println("[3]" +  UBICACION_CONCIERTO[2] + " - Precio $" + PRECIOS_UBICACION_CONCIERTO[2] + " - Asientos Disponibles " + asientosDisponiblesConcierto[2]);
                    System.out.println("[4]" +  UBICACION_CONCIERTO[3] + " - Precio $" + PRECIOS_UBICACION_CONCIERTO[3] + " - Asientos Disponibles " + asientosDisponiblesConcierto[3]);
                    System.out.println("[5]" +  UBICACION_CONCIERTO[4] + " - Precio $" + PRECIOS_UBICACION_CONCIERTO[4] + " - Asientos Disponibles " + asientosDisponiblesConcierto[4]);
                    System.out.println("++++  Elige la ubicación para el concierto: " );
                    opcionEvento = respuestaUsuario.nextInt();

                    if(opcionEvento < 1 || opcionEvento > 5){
                      System.out.println("\n+++ ERROR: Debes ingresar un número válido para poder continuar. +++" );
                    }
                  }
                  catch(InputMismatchException error){
                    System.out.println("+++ ERROR: Debes ingresar un número. +++" );
                    respuestaUsuario.next();
                    opcionEvento = 0;
                  }
                } while(opcionEvento < 1 || opcionEvento > 5);

                //Validamos que la ubicación tenga asientos para descontarlo de la ubicación elegida
                asientosDisponibles = asientosDisponiblesConcierto[opcionEvento - 1];
                if(asientosDisponibles > 0){
                  asientosDisponiblesConcierto[opcionEvento - 1] = asientosDisponibles - 1;
                  cargarVariablesEstado(opcionEvento - 1, SECCIONES_TEATRO_MORO, 1, EVENTO_TEATRO_MORO_CONCIERTO, UBICACION_CONCIERTO, PRECIOS_UBICACION_CONCIERTO);
                  System.out.println("++++  Seleccionaste la ubicación: " + eleccionUbicacion + " - Precio: $" + eleccionPrecio + "  ++++");
                  cargarListas();
                  calcularDescuento();

                  //Aumentar el contador de entradas elegidas
                  entradasElegidas++;
                  generarCodigo();
                  mostrarResumen();
                }
                else {
                  System.out.println("++++  No hay asientos disponibles para esta ubicación  ++++");
                }
                break;

              //CARTELERA PELÍCULA
              case 3:
                do {
                  try{
                    System.out.println("\n ++++++++++  PELÍCULA: " + EVENTO_TEATRO_MORO_PELICULA.toUpperCase() + "  ++++++++++");
                    System.out.println("[1]" +  UBICACION_PELICULA[0] + " - Precio $" + PRECIOS_UBICACION_PELICULA[0] + " - Asientos Disponibles " + asientosDisponiblesPelicula[0]);
                    System.out.println("[2]" +  UBICACION_PELICULA[1] + " - Precio $" + PRECIOS_UBICACION_PELICULA[1] + " - Asientos Disponibles " + asientosDisponiblesPelicula[1]);
                    System.out.println("[3]" +  UBICACION_PELICULA[2] + " - Precio $" + PRECIOS_UBICACION_PELICULA[2] + " - Asientos Disponibles " + asientosDisponiblesPelicula[2]);
                    System.out.println("++++ Elige la ubicación para la película: " );
                    opcionEvento = respuestaUsuario.nextInt();

                    if(opcionEvento < 1 || opcionEvento > 3){
                      System.out.println("\n+++ ERROR: Debes ingresar un número válido para poder continuar. +++" );
                    }
                  }
                  catch(InputMismatchException error){
                    System.out.println("+++ ERROR: Debes ingresar un número. +++" );
                    respuestaUsuario.next();
                    opcionEvento = 0;
                  }

                } while (opcionEvento < 1 || opcionEvento > 3);

                //Validamos que la ubicación tenga asientos para descontarlo de la ubicación elegida
                asientosDisponibles = asientosDisponiblesPelicula[opcionEvento - 1];
                if(asientosDisponibles > 0){
                  asientosDisponiblesPelicula[opcionEvento - 1] = asientosDisponibles - 1;
                  cargarVariablesEstado(opcionEvento - 1, SECCIONES_TEATRO_MORO, 2, EVENTO_TEATRO_MORO_PELICULA, UBICACION_PELICULA, PRECIOS_UBICACION_PELICULA);
                  System.out.println("++++  Seleccionaste la ubicación: " + eleccionUbicacion + " - Precio: $" + eleccionPrecio + "  ++++");
                  cargarListas();
                  calcularDescuento();

                  //Aumentar el contador de entradas elegidas
                  entradasElegidas++;
                  generarCodigo();
                  mostrarResumen();
                }
                else {
                  System.out.println("++++  No hay asientos disponibles para esta ubicación  ++++");
                }
                break;

              //CARTELERA CHARLA
              case 4:
                do {
                  try {
                    System.out.println("\n ++++++++++  CHARLA: " + EVENTO_TEATRO_MORO_CHARLA.toUpperCase() + "  ++++++++++");
                    System.out.println("[1]" +  UBICACION_CHARLA[0] + " - Precio $" + PRECIOS_UBICACION_CHARLA[0] + " - Asientos Disponibles " + asientosDisponiblesCharla[0]);
                    System.out.println("++++ Elige la ubicación para la charla: " );
                    opcionEvento = respuestaUsuario.nextInt();

                    if(opcionEvento != 1){
                      System.out.println("\n+++ ERROR: Debes ingresar un número válido para poder continuar. +++" );
                    }
                  }
                  catch(InputMismatchException error){
                    System.out.println("+++ ERROR: Debes ingresar un número. +++" );
                    respuestaUsuario.next();
                    opcionEvento = 0;
                  }
                } while (opcionEvento != 1);

                //Validamos que la ubicación tenga asientos para descontarlo de la ubicación elegida
                asientosDisponibles = asientosDisponiblesCharla[opcionEvento - 1];
                if(asientosDisponibles > 0){
                  asientosDisponiblesCharla[opcionEvento - 1] = asientosDisponibles - 1;
                  cargarVariablesEstado(opcionEvento - 1, SECCIONES_TEATRO_MORO, 3, EVENTO_TEATRO_MORO_CHARLA, UBICACION_CHARLA, PRECIOS_UBICACION_CHARLA);
                  System.out.println("++++  Seleccionaste la ubicación: " + eleccionUbicacion + " - Precio: $" + eleccionPrecio + "  ++++");
                  cargarListas();
                  calcularDescuento();

                  //Aumentar el contador de entradas elegidas
                  entradasElegidas++;
                  generarCodigo();
                  mostrarResumen();
                }
                else {
                  System.out.println("++++  No hay asientos disponibles para esta ubicación  ++++");
                }
                break;

              //CARTELERA STAND UP COMEDY
              case 5:
                do {
                  try {
                    System.out.println("\n ++++++++++  STAND UP COMEDY: " + EVENTO_TEATRO_MORO_STANDUP.toUpperCase() + "  ++++++++++");
                    System.out.println("[1]" +  UBICACION_STANDUP[0] + " - Precio $" + PRECIOS_UBICACION_STANDUP[0] + " - Asientos Disponibles " + asientosDisponiblesStandUp[0]);
                    System.out.println("[2]" +  UBICACION_STANDUP[1] + " - Precio $" + PRECIOS_UBICACION_STANDUP[1] + " - Asientos Disponibles " + asientosDisponiblesStandUp[1]);
                    System.out.println("++++  Elige la ubicación para el stand-up comedy: " );
                    opcionEvento = respuestaUsuario.nextInt();

                    if(opcionEvento != 1 && opcionEvento != 2){
                      System.out.println("\n+++ ERROR: Debes ingresar un número válido para poder continuar. +++" );
                    }
                  }
                  catch(InputMismatchException error){
                    System.out.println("+++ ERROR: Debes ingresar un número. +++" );
                    respuestaUsuario.next();
                    opcionEvento = 0;
                  }
                } while (opcionEvento != 1 && opcionEvento != 2);

                //Validamos que la ubicación tenga asientos para descontarlo de la ubicación elegida
                asientosDisponibles = asientosDisponiblesStandUp[opcionEvento - 1];
                if(asientosDisponibles > 0){
                  asientosDisponiblesStandUp[opcionEvento - 1] = asientosDisponibles - 1;
                  cargarVariablesEstado(opcionEvento - 1, SECCIONES_TEATRO_MORO, 4, EVENTO_TEATRO_MORO_STANDUP, UBICACION_STANDUP, PRECIOS_UBICACION_STANDUP);
                  System.out.println("++++  Seleccionaste la ubicación: " + eleccionUbicacion + " - Precio: $" + eleccionPrecio + "  ++++");
                  cargarListas();
                  calcularDescuento();

                  //Aumentar el contador de entradas elegidas
                  entradasElegidas++;
                  generarCodigo();
                  mostrarResumen();
                }
                else {
                  System.out.println("++++  No hay asientos disponibles para esta ubicación  ++++");
                }
                break;
            }

          } while(opcionCartelera < 1 || opcionCartelera > 5);

          break;

        //MENU PRINCIPAL - OPCIÓN REVISAR BOLETOS
        case 3:
          /*
          Validamos primero con el registro total de ventas para mostrar el resumen solo si se han elegido
          ubicaciones en calquier tipo de evento
          */
          if(entradasElegidas == 0){
            System.out.println("\n ++++  NO HAY UBICACIONES ELEGIDAS PARA NINGÚN EVENTO  ++++\"");

          }
          else {
            System.out.println("\n ++++++++++  RESUMEN EVENTO & UBICACIÓN  ++++++++++");
            mostrarTodosLosBoletos();
          }
          break;

        //MENU PRINCIPAL - OPCION EDITAR CÓDIGO ENTRADA
        case 4:
          if (entradasElegidas == 0) {
            System.out.println("\n +++++  NO HAY ENTRADAS PARA EDITAR  +++++");
          }
          else {
            System.out.println("\n++++++++++  EDITA EL CÓDIGO DE LA ENTRADA  ++++++++++");
            System.out.println("++++          Lista de ubicaciones elegidas       +++++");

            int indiceEditarUbicacion = 0;

            do {
              try {
                for(int i = 0; i < entradasElegidas; i++){
                  System.out.println("Entrada #" + (i + 1));
                  System.out.println("Código Entrada: " + listaEleccionCodigoCliente.get(i));
                  System.out.println("Sección: " + listaEleccionSeccion.get(i) + " - Evento: " + listaEleccionEvento.get(i) + " - Ubicación: " + listaEleccionUbicacion.get(i));
                }
                System.out.println("++++  Elige el número de entrada a la cual deseas editar su código  +++++");
                indiceEditarUbicacion = respuestaUsuario.nextInt();
                if (indiceEditarUbicacion < 1 || indiceEditarUbicacion > entradasElegidas){
                  System.out.println("\n+++ ERROR: Debes ingresar un número válido para poder continuar. +++" );
                }
              }
              catch(InputMismatchException error){
                System.out.println("+++ ERROR: Debes ingresar un número. +++" );
                respuestaUsuario.next();
                indiceEditarUbicacion = 0;
              }
            } while( indiceEditarUbicacion < 1 || indiceEditarUbicacion > entradasElegidas );

            int nuevoCodigoCliente = 0;
            int nuevoCodigoRandom = (int) (Math.random() * 9001) + 1000;

            do {
              try {
                System.out.println("++++ Ingresa el número que aparece a continuación para editar el código de la entrada:");
                System.out.println("*La persona con este código podrá esar la entrada al momento le llegar al evento");
                System.out.println(nuevoCodigoRandom);
                nuevoCodigoCliente = respuestaUsuario.nextInt();
                if (nuevoCodigoCliente != nuevoCodigoRandom) {
                  System.out.println("\n+++ ERROR: Debes ingresar el mismo código que aparece en pantalla poder generar la entrada. +++");
                }
              }
              catch(InputMismatchException error){
                System.out.println("+++ ERROR: Debes ingresar dígitos. +++" );
                respuestaUsuario.next();
                nuevoCodigoCliente = 0;
              }

            } while(nuevoCodigoCliente != nuevoCodigoRandom);

            listaEleccionCodigoCliente.set(indiceEditarUbicacion - 1,nuevoCodigoCliente);
            opcionCodigo = (int) (Math.random() * 9001) + 1000;
            System.out.println("++++ EL CÓDIGO DE LA ENTRADA SE HA EDITADO CORRECTAMENTE  ++++");
          }
          break;

        //MENU PRINCIPAL - OPCION ELIMINAR
        case 5:
          if(entradasElegidas == 0){
            System.out.println("\n++++  NO HAY UBICACIONES ELEGIDAS PARA ELIMINAR  ++++");
          }
          else {
            System.out.println("\n++++++++++  ELIMINAR UBICACIÓN  +++++++++++ ");
            System.out.println("++++++  Lista de ubicaciones elegidas  +++++");

            int indiceEliminarEntrada = 0;
            do {
              try {
                for(int i = 0; i < entradasElegidas; i++){
                  System.out.println("Entrada #" + (i + 1) + " - Evento: " + listaEleccionEvento.get(i) + " - Ubicación: " + listaEleccionUbicacion.get(i));
                }
                System.out.println("++++  ¿Qué entrada deseas eliminar?  ++++");
                indiceEliminarEntrada = respuestaUsuario.nextInt();

                if(indiceEliminarEntrada < 0 || indiceEliminarEntrada > entradasElegidas){
                  System.out.println("\n+++ ERROR: Debes ingresar un número válido para poder continuar. +++" );
                }
              }
              catch(InputMismatchException error){
                System.out.println("+++ ERROR: Debes ingresar un número. +++" );
                respuestaUsuario.next();
                opcionCartelera = 0;
              }
            } while(indiceEliminarEntrada < 0 || indiceEliminarEntrada > entradasElegidas);

            listaEleccionCodigoCliente.remove(indiceEliminarEntrada - 1);
            listaEleccionEvento.remove(indiceEliminarEntrada - 1);
            listaEleccionUbicacion.remove(indiceEliminarEntrada - 1);
            listaEleccionPrecio.remove(indiceEliminarEntrada - 1);
            listaEleccionValorDescuento.remove(indiceEliminarEntrada -1 );

            //Aumentar el número de entradas disponibles para esa sección a la lista que corresponda.
            switch (listaEleccionSeccion.get(indiceEliminarEntrada - 1)){
              case "Teatro":
                asientosDisponibles = asientosDisponiblesObra[indiceEliminarEntrada - 1];
                asientosDisponiblesObra[indiceEliminarEntrada - 1] = asientosDisponibles + 1;
                break;
              case "Concierto":
                asientosDisponibles = asientosDisponiblesConcierto[indiceEliminarEntrada - 1];
                asientosDisponiblesConcierto[indiceEliminarEntrada - 1] = asientosDisponibles + 1;
                break;
              case "Película":
                asientosDisponibles = asientosDisponiblesPelicula[indiceEliminarEntrada - 1];
                asientosDisponiblesPelicula[indiceEliminarEntrada - 1] = asientosDisponibles + 1;
                break;
              case "Charla":
                asientosDisponibles = asientosDisponiblesCharla[indiceEliminarEntrada - 1];
                asientosDisponiblesCharla[indiceEliminarEntrada - 1] = asientosDisponibles + 1;
                break;
              case "Standup":
                asientosDisponibles = asientosDisponiblesStandUp[indiceEliminarEntrada - 1];
                asientosDisponiblesStandUp[indiceEliminarEntrada - 1] = asientosDisponibles + 1;
                break;
            }
            listaEleccionSeccion.remove(indiceEliminarEntrada - 1);
            //Descontar 1 al número de asientos a la ubicación elegidas
            entradasElegidas--;

            System.out.println("\n+++ La entrada se ha eliminado exitosamente +++");
          }
          break;
      }
    }

    //MENU PRINCIPAL - OPCION FINALIZAR COMPRA / SALIDA DEL SISTEMA
    if(entradasElegidas == 0){
      System.out.println("++++ HAS SALIDO DEL SISTEMA DE GESTIÓN DE COMPRAS ++++");
    }
    else {
      System.out.println("++++++++++++++++++ BOLETA  ++++++++++++++++++");
      int subtotalBoleta = 0;
      for(int i = 0; i < entradasElegidas; i++){
        subtotalBoleta += listaEleccionPrecio.get(i);
      }
      int totalDescuentos = 0;
      for(int i = 0; i < entradasElegidas; i++){
        totalDescuentos += listaEleccionValorDescuento.get(i);
      }
      int totalBoleta = subtotalBoleta - totalDescuentos;

      mostrarTodosLosBoletos();
      System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
      System.out.println("TOTAL ENTRADAS: " + entradasElegidas);
      System.out.println("SUBTOTAL: $" + subtotalBoleta);
      System.out.println("DESCUENTOS: $" + totalDescuentos);
      System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
      System.out.println("TOTAL A PAGAR: $" + totalBoleta);
      System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
      System.out.println("++++  Gracias por utilizar el Sistema de Gestión de Entradas  ++++");
    }
    
  }// FIN MAIN
  
}
