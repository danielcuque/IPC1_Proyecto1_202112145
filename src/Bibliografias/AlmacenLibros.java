package Bibliografias;

public class AlmacenLibros {

    private static Bibliografias[] arregloBibliografias = new Bibliografias[100];
    private static int  cantidadBibliografias = 0;

    public AlmacenLibros(){
    }

    public static void crearBibliografia(Bibliografias nuevoLibro){
        for (int i = 0; i < arregloBibliografias.length; i++) {
            if(arregloBibliografias[i] == null){
                arregloBibliografias[i] = nuevoLibro;
                cantidadBibliografias++;
                return;
            }
        }
    }

    public static void actualizarLibro(String tipo,
                                       String autor,
                                       String titulo,
                                       String descripcion,
                                       String edicion,
                                       String temas,
                                       String frecuencia,
                                       String ejemplares,
                                       String area,
                                       String copias,
                                       String disponibles){

        for (int i = 0; i < (arregloBibliografias.length - 1); i++) {
            if(arregloBibliografias[i] != null){
                if(arregloBibliografias[i].getTitulo().equals(titulo)){
                    arregloBibliografias[i].setTitulo(tipo);
                    arregloBibliografias[i].setAutor(autor);
                    arregloBibliografias[i].setDescripcion(descripcion);
                    //Propiedades de Edicion
                    arregloBibliografias[i].setStrEdicion(edicion);
                    arregloBibliografias[i].setEdicion(Integer.parseInt(edicion));
                    //Propiedades de Temas
                    arregloBibliografias[i].setTemas(temas.trim().split(";"));
                    arregloBibliografias[i].setStrTemas(temas);
                    arregloBibliografias[i].setTemasConcatenados(temas);
                    arregloBibliografias[i].setFrecuenciaActual(frecuencia);
                    //Propiedades de Ejemplares
                    arregloBibliografias[i].setStrEjemplares(ejemplares);
                    arregloBibliografias[i].setEjemplares(Integer.parseInt(ejemplares));
                    arregloBibliografias[i].setArea(area);
                    //Propiedades de Copias
                    arregloBibliografias[i].setStrCopias(copias);
                    arregloBibliografias[i].setCopias(Integer.parseInt(copias));
                    //Propiedades de Disponibles
                    arregloBibliografias[i].setStrDisponibles(disponibles);
                    arregloBibliografias[i].setDisponibles(Integer.parseInt(disponibles));
                    break;
                }
            }
        }

    }

    public static void eliminarLibro(String titulo){
        for (int i = 0; i < (arregloBibliografias.length - 1); i++) {
            if(arregloBibliografias[i] != null){
                if(arregloBibliografias[i].getTitulo().equals(titulo)){
                    arregloBibliografias[i] = null;
                    break;
                }
            }
        }

        for (int i = 0; i < (arregloBibliografias.length - 1); i++) {
            if(arregloBibliografias[i] == null || arregloBibliografias[i+1] != null){
                arregloBibliografias[i] = arregloBibliografias[i+1];
                arregloBibliografias[i+1] = null;
            }
        }
    }


    public static String[][] buscarTemaLibro(String buscar){
        String datos[][] = new String[cantidadBibliografias][11];
        int pos = 0;
        boolean verificarTitulo = false;
        boolean verificarTema = false;
        for(Bibliografias bibliografias: arregloBibliografias){
            if(bibliografias != null){
                if(bibliografias.getTitulo().equalsIgnoreCase(buscar)){
                    verificarTitulo = true;
                }
                for (String temas :
                        bibliografias.getTemas()) {
                    if (temas.trim().equalsIgnoreCase(buscar)) {
                        verificarTema = true;
                        break;
                    } else{
                        verificarTema = false;
                    }
                }

                if(verificarTitulo || verificarTema){
                    String [] fila = {
                            bibliografias.getTipo(),
                            bibliografias.getAutor(),
                            bibliografias.getTitulo(),
                            bibliografias.getDescripcion(),
                            bibliografias.getStrEdicion(),
                            bibliografias.getTemasConcatenados(),
                            bibliografias.getFrecuenciaActual(),
                            bibliografias.getStrEjemplares(),
                            bibliografias.getArea(),
                            bibliografias.getStrCopias(),
                            bibliografias.getStrDisponibles(),
                    };
                    datos[pos] = fila;
                    pos++;
                }
            }
        }

        return datos;
    }

    public static String[] cabeceraLibros(){
        String [] cabecera =  {
                "TIPO",
                "AUTOR",
                "TITULO",
                "DESCRIPCION",
                "EDICION",
                "TEMAS",
                "FRECUENCIA ACTUAL",
                "EJEMPLARES",
                "ÁREA",
                "COPIAS",
                "DISPONIBLES"
        };
        return cabecera;
    }

    public static String[][] obtenerLibros(){
        String datos[][] = new String[cantidadBibliografias][11];
        int pos = 0;
        for(Bibliografias bibliografias: arregloBibliografias){
            if(bibliografias != null){
                String [] fila = {
                        bibliografias.getTipo(),
                        bibliografias.getAutor(),
                        bibliografias.getTitulo(),
                        bibliografias.getDescripcion(),
                        bibliografias.getStrEdicion(),
                        bibliografias.getTemasConcatenados(),
                        bibliografias.getFrecuenciaActual(),
                        bibliografias.getStrEjemplares(),
                        bibliografias.getArea(),
                        bibliografias.getStrCopias(),
                        bibliografias.getStrDisponibles(),
                };
                datos[pos] = fila;
                pos ++;
            }
        }
        return datos;
    }

    public static String validarCampos(String autor,
                                String titulo,
                                String edicion,
                                String descripcion,
                                String temas,
                                String frecuencia,
                                String ejemplares,
                                String area,
                                String copia,
                                String disponibles){
        String mensajeAlerta = "";
        if(autor.length() == 0
                || titulo.length() == 0 //String
                || edicion.length() == 0 //int
                || descripcion.length() == 0 //String
                || temas.length() == 0 //String
                || frecuencia.length() == 0 //String
                || ejemplares.length() == 0 //int
                || area.length() == 0 //String
                || copia.length() == 0 //int
                || disponibles.length() == 0 //int
        ){
            mensajeAlerta = "DEBE DE LLENAR TODOS LOS CAMPOS";
        } else{
            /*Valida que los caracteres sean letras, ya sea mayúsculas o minúsculas
            mediante una expresión regular, además, puede incluir espacios en blanco,
            puntos(.),guiones(-) o comas (,)
             */
            if(!autor.matches("^[A-Za-z ,.-]*$")
                    || !titulo.trim().matches("^[A-Za-z ,.-]*$")
                    || !descripcion.trim().matches("^[A-Za-z ,.-]*$")
                    || !temas.trim().matches("^[A-Za-z ,.-]*$")
                    || !frecuencia.trim().matches("^[A-Za-z ,.-]*$")
                    || !area.trim().matches("^[A-Za-z ,.-]*$")
            ){
                mensajeAlerta = "SOLO SE PERMITEN LETRAS";

            }
            /*
            Valida que solo se ingresen números por medio de la
            expresión regular donde agrupa los carácteres
            de 0-1-2-3-4-5-6-7-8-9
            */

            else if( !edicion.matches("^[0-9]+$")
                    || !ejemplares.matches("^[0-9]+$")
                    || !copia.matches("^[0-9]+$")
                    || !disponibles.matches("^[0-9]+$")){
                mensajeAlerta = "SOLO SE PERMITEN NUMEROS";
            }
        }
        return mensajeAlerta;
    }
}
