package estructuras.arboles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsciiBox {

    // ── Caracteres de marco ──────────────────────────────────────────────────
    static String esquinaSupIzq  = "╔";
    static String esquinaSupDer  = "╗";
    static String esquinaInfIzq  = "╚";
    static String esquinaInfDer  = "╝";
    static String lineaVertical  = "║";
    static String lineaHorizont  = "═";
    static String unionIzqMedio  = "╠";
    static String unionDerMedio  = "╣";
    static String unionInfMedio  = "╩";
    static String unionSupMedio  = "╦";
    static String unionCentral   = "╬";
    static String parrafo        = "¶";

    // ── Vocales y letras especiales con tilde (Windows) ──────────────────────
    static String accentA        = "á";
    static String accentE        = "é";
    static String accentI        = "í";
    static String accentO        = "ó";
    static String accentU        = "ú";
    static String accentUDier    = "ü";
    static String accentN        = "ñ";
    static String accentAMay     = "Á";
    static String accentEMay     = "É";
    static String accentIMay     = "Í";
    static String accentOMay     = "Ó";
    static String accentUMay     = "Ú";
    static String accentUDierMay = "Ü";
    static String accentNMay     = "Ñ";
    static String interrogAbre   = "¿";
    static String exclamAbre     = "¡";

    // ── Códigos ANSI – reset y colores de texto ───────────────────────────────
    static String RESET    = "\u001b[0m";
    static String BLACK    = "\u001b[30m";
    static String B_BLACK  = "\u001b[90m";
    static String RED      = "\u001b[31m";
    static String B_RED    = "\u001b[91m";
    static String GREEN    = "\u001b[32m";
    static String B_GREEN  = "\u001b[92m";
    static String YELLOW   = "\u001b[33m";
    static String B_YELLOW = "\u001b[93m";
    static String BLUE     = "\u001b[34m";
    static String B_BLUE   = "\u001b[94m";
    static String PURPLE   = "\u001b[35m";
    static String B_PURPLE = "\u001b[95m";
    static String CYAN     = "\u001b[36m";
    static String B_CYAN   = "\u001b[96m";
    static String WHITE    = "\u001b[37m";
    static String B_WHITE  = "\u001b[97m";

    // ── Códigos ANSI – fondos ────────────────────────────────────────────────
    static String BG_BLACK    = "\u001b[40m";
    static String BGB_BLACK   = "\u001b[100m";
    static String BG_RED      = "\u001b[41m";
    static String BGB_RED     = "\u001b[101m";
    static String BG_GREEN    = "\u001b[42m";
    static String BGB_GREEN   = "\u001b[102m";
    static String BG_YELLOW   = "\u001b[43m";
    static String BGB_YELLOW  = "\u001b[103m";
    static String BG_BLUE     = "\u001b[44m";
    static String BGB_BLUE    = "\u001b[104m";
    static String BG_PURPLE   = "\u001b[45m";
    static String BGB_PURPLE  = "\u001b[105m";
    static String BG_CYAN     = "\u001b[46m";
    static String BGB_CYAN    = "\u001b[106m";
    static String BG_WHITE    = "\u001b[47m";
    static String BGB_WHITE   = "\u001b[107m";

    // ── Códigos ANSI – estilos de texto ──────────────────────────────────────
    static String BOLD    = "\u001b[1m";
    static String FAINT   = "\u001b[2m";
    static String ITALIC  = "\u001b[3m";
    static String UNDERL  = "\u001b[4m";
    static String BLINK   = "\u001b[5m";
    static String HIDE    = "\u001b[8m";
    static String STRIKE  = "\u001b[9m";
    static String D_UNDERL = "\u001b[21m";

    // ── Arte ASCII para mensajes de error ────────────────────────────────────
    static String exception =
        " -hoo+. -.\n" +
        " -d++ooo/. :++s/\n" +
        " :y/oo+:/oo/ -///oo++s \n" +
        " /h/++os+../++/--:////:::////:--::/+/ -:///::/oso:-y \n" +
        " os-:o++so .. . .-. ./+////////++/-./+ss+:-..y. \n" +
        " ss:-/++o -/os++/-:--/y: \n" +
        " /y/::- /o++o++:-. -s \n" +
        " /s--. :++o+o+:...+o \n" +
        " d-- ...-:.::.s: \n" +
        " o+-. -- :y \n" +
        " :y-- . h \n" +
        " .y--.oNNd:- -/+--/:- o/ \n" +
        " h--. :mMMdo +dMMMMmmms: h \n" +
        " .y-. .-- /osyso/. .s \n" +
        " -y- .. ` d \n" +
        " h-. ++//::. `- :y \n" +
        " y- hsoooyso. ... y: \n" +
        " s- +yy: --. /y \n" +
        " -+. . .:- .-- .d. \n" +
        " y. .:yhddddhy+: ./-- ` +s: \n" +
        " y. :ddyyyyyhddhs+//+os/- .. :s \n" +
        " s:-. . o+///:///+syhys+..-. -y \n" +
        " .s-. .- -/. - .. .y \n" +
        " -o- .+:.-. -+. oo/ \n" +
        " o:::-------/oooooooo+:----------------------------:sd \n" +
        " -------------------------------------+o++++s++/:+ooo: \n";

    // ── Estado general ────────────────────────────────────────────────────────
    static String entrada = "";
    static Scanner scanner;
    static long inicio;
    static long finaly;
    static long tiempo;

    // ── Estado del procesamiento de colores ANSI ──────────────────────────────
    private static boolean hasEscapeS;
    private static int colorSize;
    private static String colorSafe;

    // ── Expresión regular para secuencias ANSI ────────────────────────────────
    static Pattern ansiExpresion;
    static Matcher matcher;

    // ── Constructor ───────────────────────────────────────────────────────────
    public AsciiBox() { }

    // ─────────────────────────────────────────────────────────────────────────
    //  asciiBox – genera un cuadro de texto con borde
    // ─────────────────────────────────────────────────────────────────────────
    public static String asciiBox(String texto, boolean justificado, int anchoBase) {
        String resultado      = "";
        int    lineaActual    = 1;
        int    espaciosFaltan = 0;
        boolean saltoLinea    = false;

        anchoBase += 5;
        texto = isWindows() ? addAccents(texto) : texto;
        texto = texto.replaceAll("\n", "0x0a ");
        texto = texto.replaceAll("\t", "    ");

        String[] palabras = texto.split(" ");
        palabras = checkWordsLength(palabras, anchoBase - 5);

        resultado = addFrame(esquinaSupIzq, esquinaSupDer, resultado, anchoBase - 1, true);
        resultado = resultado + lineaVertical;
        ++lineaActual;

        try {
            for (String palabra : palabras) {
                if (palabra.contains("0x0a")) {
                    saltoLinea = true;
                    palabra = palabra.replaceAll("0x0a", "");
                }

                if (length(resultado) + length(palabra) + 1 <= anchoBase * lineaActual - 3) {
                    resultado = resultado + " " + palabra;
                } else {
                    if (justificado) {
                        resultado = justify(lineaActual, anchoBase, resultado);
                    }
                    espaciosFaltan = anchoBase * lineaActual - 3 - length(resultado);
                    resultado = addBlanks(resultado, espaciosFaltan);

                    if (hasEscapeS) {
                        resultado = resultado + " " + RESET + lineaVertical + "\n" + lineaVertical + colorSafe + " " + palabra;
                    } else {
                        resultado = resultado + " " + lineaVertical + "\n" + lineaVertical + " " + palabra;
                    }
                    ++lineaActual;
                }

                if (saltoLinea) {
                    espaciosFaltan = anchoBase * lineaActual - 3 - length(resultado);
                    resultado = addBlanks(resultado, espaciosFaltan);
                    resultado = resultado + " " + lineaVertical + "\n" + lineaVertical;
                    ++lineaActual;
                    saltoLinea = false;
                }
            }

            espaciosFaltan = anchoBase * lineaActual - 2 - length(resultado);
            resultado = addBlanks(resultado, espaciosFaltan);
            resultado = resultado + lineaVertical + "\n";
            ++lineaActual;
            resultado = addFrame(esquinaInfIzq, esquinaInfDer, resultado, anchoBase - 1, true);

        } catch (Exception ex) {
            show(asciiBox((String) exception, false, 69));
            show(asciiBox(ex.getMessage(), false, ex.toString().length()));
            resultado = "";
        }

        hasEscapeS = false;
        colorSize  = 0;
        colorSafe  = "";
        return resultado;
    }

    public static String asciiBox(String texto, boolean justificado) {
        return asciiBox(texto, justificado, 90);
    }

    public static String asciiBox(Object objeto, boolean justificado, int anchoBase) {
        return asciiBox(objeto.toString(), justificado, anchoBase);
    }

    public static String asciiBox(Object objeto, boolean justificado) {
        return asciiBox(objeto.toString(), justificado, 90);
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  justify – justifica el texto dentro del ancho dado
    // ─────────────────────────────────────────────────────────────────────────
    private static String justify(int lineaActual, int ancho, String texto) {
        String espacio         = " ";
        int    posicionEspacio = 0;
        int    espaciosFaltan  = ancho * lineaActual - 3 - length(texto);
        int    inicioLinea     = ancho * (lineaActual - 1) + 2 + colorSize;

        for (int i = 0; i < espaciosFaltan; ++i) {
            posicionEspacio = texto.indexOf(" ", inicioLinea);
            if (posicionEspacio == -1) {
                length(texto);
                inicioLinea     = ancho * (lineaActual - 1) + 2 + colorSize;
                posicionEspacio = texto.indexOf(" ", inicioLinea);
                if (posicionEspacio == -1) break;
            }
            String palabraActual = texto.substring(inicioLinea, posicionEspacio);
            inicioLinea += length(palabraActual) + 3;

            String parteIzq = texto.substring(0, posicionEspacio);
            String parteDer = texto.substring(posicionEspacio + 1);
            texto = parteIzq + espacio + parteDer;
        }
        return texto;
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  nodify – modifica los bordes de un cuadro según el tipo de nodo
    // ─────────────────────────────────────────────────────────────────────────
    public static String nodify(String cuadro, int tipoNodo) {
        String[] lineas       = cuadro.split("\n");
        String   resultado    = "";
        String   marcoSuperior = "";
        String   marcoInferior = "";
        int      anchoLinea   = length(lineas[0]);

        switch (tipoNodo) {
            case 1:
                marcoInferior = addFrameCP(esquinaInfIzq, unionSupMedio, esquinaInfDer, lineaHorizont, marcoInferior, anchoLinea, true);
                for (int i = 0; i < lineas.length - 1; ++i) {
                    resultado = resultado + lineas[i] + "\n";
                }
                resultado = resultado + marcoInferior;
                break;

            case 2:
                marcoSuperior = addFrameCP(esquinaSupIzq, unionInfMedio, esquinaSupDer, lineaHorizont, marcoSuperior, anchoLinea, true);
                marcoInferior = addFrameCP(esquinaInfIzq, unionSupMedio, esquinaInfDer, lineaHorizont, marcoInferior, anchoLinea, true);
                resultado = resultado + marcoSuperior;
                for (int i = 1; i < lineas.length - 1; ++i) {
                    resultado = resultado + lineas[i] + "\n";
                }
                resultado = resultado + marcoInferior;
                break;

            case 3:
                marcoSuperior = addFrameCP(esquinaSupIzq, unionInfMedio, esquinaSupDer, lineaHorizont, marcoSuperior, anchoLinea, true);
                resultado = resultado + marcoSuperior;
                for (int i = 1; i < lineas.length; ++i) {
                    resultado = resultado + lineas[i] + "\n";
                }
                break;

            default:
                resultado = cuadro;
        }
        return resultado;
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  addTreeEdge – agrega el borde superior de conexión en un árbol
    // ─────────────────────────────────────────────────────────────────────────
    public static String addTreeEdge(String arbol) {
        String[] lineas      = arbol.split("\n");
        int      posIzq      = 0;
        int      posDer      = 0;
        int      anchoConex  = 0;
        String   bordeConex  = "";

        posIzq     = lineas[0].indexOf(unionInfMedio);
        posDer     = lineas[0].lastIndexOf(unionInfMedio);
        anchoConex = posDer - posIzq + 1;

        if (anchoConex == 1) {
            bordeConex = bordeConex + lineaVertical;
        } else {
            bordeConex = addFrame(esquinaSupIzq, esquinaSupDer, bordeConex, anchoConex, false);
            char[]        charsLinea = lineas[0].substring(posIzq, posDer + 1).toCharArray();
            StringBuilder sb         = new StringBuilder(bordeConex);
            for (int i = 1; i < charsLinea.length - 1; ++i) {
                if (Character.compare(unionInfMedio.charAt(0), charsLinea[i]) == 0) {
                    sb.replace(i, i + 1, unionSupMedio);
                }
            }
            bordeConex = sb.toString();
        }

        bordeConex = addOffSet(bordeConex, posIzq);
        return bordeConex + "\n" + arbol;
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  treeConcat – une dos sub-árboles lado a lado
    // ─────────────────────────────────────────────────────────────────────────
    public static String treeConcat(String arbolIzq, String arbolDer) {
        String   resultadoIzq  = "";
        String   resultadoDer  = "";
        String   marcoDer      = "";
        int      inicioContenido = 0;
        int      finContenido    = 0;
        int      anchoContenido  = 0;

        String[] lineasIzq = arbolIzq.split("\n");
        String[] lineasDer = arbolDer.split("\n");
        char[]   charsPrimLinea = lineasDer[0].toCharArray();

        // Detectar inicio y fin del contenido en la primera línea del árbol derecho
        for (int i = 0; i < charsPrimLinea.length; ++i) {
            if (!Character.isWhitespace(charsPrimLinea[i])) {
                inicioContenido = i;
                break;
            }
        }
        for (int i = charsPrimLinea.length - 1; i >= 0; --i) {
            if (!Character.isWhitespace(charsPrimLinea[i])) {
                finContenido = i;
                break;
            }
        }

        anchoContenido = finContenido - inicioContenido + 1;

        int anchoIzq = anchoContenido - length(lineasIzq[0]);
        int padIzq   = anchoIzq / 2;
        int padDer   = anchoIzq - padIzq;
        padIzq += inicioContenido;

        // Centrar el árbol izquierdo
        for (int i = 0; i < lineasIzq.length; ++i) {
            resultadoIzq = addBlanks(resultadoIzq, padIzq);
            resultadoIzq = resultadoIzq + lineasIzq[i];
            resultadoIzq = addBlanks(resultadoIzq, padDer);
            resultadoIzq = resultadoIzq + "\n";
        }

        String[] lineasIzqCentradas  = resultadoIzq.split("\n");
        char[]   charsUltimaLinea    = lineasIzqCentradas[lineasIzqCentradas.length - 1].toCharArray();
        char[]   charsPrimLinDer     = lineasDer[0].toCharArray();
        char[]   charsPrimLinDerPad  = Arrays.copyOf(charsPrimLinDer, charsUltimaLinea.length);
        Arrays.fill(charsPrimLinDerPad, charsPrimLinDer.length, charsUltimaLinea.length, ' ');

        StringBuilder sbDer = new StringBuilder(lineasDer[0]);
        for (int i = 0; i < charsUltimaLinea.length; ++i) {
            if (Character.compare(unionSupMedio.charAt(0), charsUltimaLinea[i]) == 0) {
                if (Character.compare(lineaHorizont.charAt(0), charsPrimLinDerPad[i]) == 0) {
                    sbDer.replace(i, i + 1, unionInfMedio);
                } else if (Character.compare(unionSupMedio.charAt(0), charsPrimLinDerPad[i]) == 0) {
                    sbDer.replace(i, i + 1, unionCentral);
                } else if (Character.isWhitespace(charsPrimLinDerPad[i])) {
                    sbDer.replace(i - 1, i + 1, esquinaSupIzq + esquinaInfDer);
                }
                break;
            }
        }

        resultadoDer = sbDer.toString() + "\n";
        for (int i = 1; i < lineasDer.length; ++i) {
            resultadoDer = resultadoDer + lineasDer[i] + "\n";
        }

        return resultadoIzq + resultadoDer;
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  addOffSet – agrega sangría a cada línea de un bloque de texto
    // ─────────────────────────────────────────────────────────────────────────
    public static String addOffSet(String bloque, int sangria) {
        String   resultado  = "";
        String   espacios   = "";
        espacios = addBlanks(espacios, sangria);

        String[] lineas = bloque.split("\n");
        for (int i = 0; i < lineas.length; ++i) {
            if (i < lineas.length - 1) {
                resultado = resultado + espacios + lineas[i] + "\n";
            } else {
                resultado = resultado + espacios + lineas[i];
            }
        }
        return resultado;
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  concatAscii – concatena dos bloques de texto lado a lado
    // ─────────────────────────────────────────────────────────────────────────
    public static String concatAscii(String bloqueIzq, String bloqueDer, int separacion, boolean alineaSuperior) {
        String   resultado    = "";
        String   temporal     = "";
        int      anchoIzq     = 0;
        int      anchoDer     = 0;
        String[] lineasIzq    = bloqueIzq.split("\n");
        String[] lineasDer    = bloqueDer.split("\n");

        if (!bloqueIzq.equals("") && !bloqueDer.equals("")) {
            for (String linea : lineasIzq) {
                if (length(linea) > anchoIzq) anchoIzq = length(linea);
            }
            for (String linea : lineasDer) {
                if (length(linea) > anchoDer) anchoDer = length(linea);
            }

            if (lineasIzq.length < lineasDer.length) {
                int diferencia = lineasDer.length - lineasIzq.length;
                if (alineaSuperior) {
                    String[] extended = (String[]) Arrays.copyOf(lineasIzq, lineasDer.length);
                    lineasIzq = extended;
                    for (int i = 0; i < lineasIzq.length; ++i) {
                        if (lineasIzq[i] == null) {
                            temporal   = "";
                            lineasIzq[i] = addBlanks(temporal, anchoIzq);
                        }
                    }
                } else {
                    String[] ajustado = new String[lineasDer.length];
                    for (int i = 0; i < diferencia; ++i) {
                        temporal     = "";
                        ajustado[i]  = addBlanks(temporal, anchoIzq);
                    }
                    for (int i = 0; i < lineasIzq.length; ++i) {
                        ajustado[i + diferencia] = lineasIzq[i];
                    }
                    lineasIzq = ajustado;
                }
            } else if (lineasDer.length < lineasIzq.length) {
                int diferencia = lineasIzq.length - lineasDer.length;
                if (alineaSuperior) {
                    String[] extended = (String[]) Arrays.copyOf(lineasDer, lineasIzq.length);
                    lineasDer = extended;
                    for (int i = 0; i < lineasDer.length; ++i) {
                        if (lineasDer[i] == null) {
                            temporal   = "";
                            lineasDer[i] = addBlanks(temporal, anchoDer);
                        }
                    }
                } else {
                    String[] ajustado = new String[lineasIzq.length];
                    for (int i = 0; i < diferencia; ++i) {
                        temporal     = "";
                        ajustado[i]  = addBlanks(temporal, anchoDer);
                    }
                    for (int i = 0; i < lineasDer.length; ++i) {
                        ajustado[i + diferencia] = lineasDer[i];
                    }
                    lineasDer = ajustado;
                }
            }

            // Rellenar lado izquierdo para que todas las líneas tengan el mismo ancho
            for (int i = 0; i < lineasIzq.length; ++i) {
                int relleno = anchoIzq - length(lineasIzq[i]);
                lineasIzq[i] = addBlanks(lineasIzq[i], relleno);
            }

            // Unir línea a línea
            for (int i = 0; i < lineasIzq.length; ++i) {
                resultado = resultado + lineasIzq[i];
                String conSep = addBlanks(resultado, separacion);
                resultado = conSep + lineasDer[i] + "\n";
            }
        } else {
            resultado = bloqueIzq.equals("") ? bloqueDer : bloqueIzq;
        }
        return resultado;
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  addBlanks – agrega espacios en blanco al final de un String
    // ─────────────────────────────────────────────────────────────────────────
    public static String addBlanks(String base, int cantidad) {
        StringBuilder sb = new StringBuilder(base);
        if (cantidad > 0) {
            for (int i = 0; i < cantidad; ++i) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  addFrame – construye una línea de borde simple
    // ─────────────────────────────────────────────────────────────────────────
    public static String addFrame(String extremoIzq, String extremoDer, String base, int ancho, boolean agregarSalto) {
        StringBuilder sb = new StringBuilder(base);
        if (ancho > 1) {
            sb.append(extremoIzq);
            for (int i = 1; i < ancho - 1; ++i) {
                sb.append(lineaHorizont);
            }
            sb.append(extremoDer);
            if (agregarSalto) sb.append("\n");
        }
        return sb.toString();
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  addFrameCP – construye una línea de borde con conector central
    // ─────────────────────────────────────────────────────────────────────────
    public static String addFrameCP(String extremoIzq, String conectorCentral, String extremoDer,
                                    String relleno, String base, int ancho, boolean agregarSalto) {
        StringBuilder sbMarco = new StringBuilder();
        int totalRelleno = ancho - 2 - 1;
        int rellenoIzq   = totalRelleno / 2;
        int rellenoDer   = totalRelleno - rellenoIzq;

        sbMarco.append(extremoIzq);
        for (int i = 0; i < rellenoIzq; ++i)   sbMarco.append(relleno);
        sbMarco.append(conectorCentral);
        for (int i = 0; i < rellenoDer; ++i)   sbMarco.append(relleno);
        sbMarco.append(extremoDer);
        if (agregarSalto) sbMarco.append("\n");

        StringBuilder sbTotal = new StringBuilder(base);
        sbTotal.append(sbMarco);
        return sbTotal.toString();
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  addAccents – reemplaza tildes Unicode por sus equivalentes de campo
    // ─────────────────────────────────────────────────────────────────────────
    public static String addAccents(String texto) {
        try {
            HashMap<String, String> mapaAcentos = new HashMap<>();
            mapaAcentos.put("á",  accentA);
            mapaAcentos.put("é",  accentE);
            mapaAcentos.put("í",  accentI);
            mapaAcentos.put("ó",  accentO);
            mapaAcentos.put("ú",  accentU);
            mapaAcentos.put("ü",  accentUDier);
            mapaAcentos.put("Á",  accentAMay);
            mapaAcentos.put("É",  accentEMay);
            mapaAcentos.put("Í",  accentIMay);
            mapaAcentos.put("Ó",  accentOMay);
            mapaAcentos.put("Ú",  accentUMay);
            mapaAcentos.put("Ü",  accentUDierMay);
            mapaAcentos.put("ñ",  accentN);
            mapaAcentos.put("Ñ",  accentNMay);
            mapaAcentos.put("¡",  exclamAbre);
            mapaAcentos.put("¿",  interrogAbre);

            for (Map.Entry<String, String> entrada : mapaAcentos.entrySet()) {
                texto = texto.replace(entrada.getKey(), entrada.getValue());
            }
        } catch (Exception ex) {
            show(asciiBox((String) exception, false, 69));
            show(asciiBox((Object) ex, false, ex.toString().length()));
        }
        return texto;
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  hasEscapeS – detecta si hay secuencias de escape ANSI en el texto
    // ─────────────────────────────────────────────────────────────────────────
    private static boolean hasEscapeS(String texto) {
        matcher = ansiExpresion.matcher(texto);
        boolean encontrado = matcher.find();
        if (encontrado) {
            hasEscapeS = true;
        }
        return encontrado;
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  length – calcula la longitud visible del texto (sin códigos ANSI)
    // ─────────────────────────────────────────────────────────────────────────
    public static int length(String texto) {
        colorSize = 0;
        if (hasEscapeS(texto)) {
            matcher   = ansiExpresion.matcher(texto);
            colorSafe = "";
            for (boolean encontrado = matcher.find(); encontrado; encontrado = matcher.find()) {
                colorSize += matcher.group().length();
                colorSafe  = matcher.group(0);
            }
            return texto.length() - colorSize;
        } else {
            return texto.length();
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  isWindows – detecta si el sistema operativo es Windows
    // ─────────────────────────────────────────────────────────────────────────
    private static Boolean isWindows() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                return true;
            }
        } catch (Exception ex) {
            show(asciiBox((String) exception, false, 69));
            show(asciiBox((Object) ex, false, ex.toString().length()));
        }
        return false;
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  show – imprime un objeto o texto en consola
    // ─────────────────────────────────────────────────────────────────────────
    public static void show(Object objeto) {
        System.out.println(objeto.toString());
    }

    public static void show(String texto) {
        System.out.println(addAccents(texto));
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  checkWordsLength – divide palabras que excedan el ancho máximo
    // ─────────────────────────────────────────────────────────────────────────
    private static String[] checkWordsLength(String[] palabras, int anchoMax) {
        ArrayList<String> resultado = new ArrayList<>();

        for (int i = 0; i < palabras.length; ++i) {
            if (length(palabras[i]) > anchoMax && !palabras[i].contains("0x0a")) {
                int partes = length(palabras[i]) / anchoMax;

                for (int parte = 0; parte < partes; ++parte) {
                    String fragmento;
                    if (parte == 0) {
                        fragmento = palabras[i].substring(parte * (anchoMax - 1), (parte + 1) * (anchoMax - 1) + colorSize);
                    } else {
                        fragmento = palabras[i].substring(parte * (anchoMax - 1) + colorSize, (parte + 1) * (anchoMax - 1) + colorSize);
                    }
                    fragmento = fragmento + "-";
                    resultado.add(fragmento);
                }

                if (length(palabras[i]) % anchoMax != 0) {
                    length(palabras[i]);
                    String resto = palabras[i].substring(partes * (anchoMax - 1) + colorSize, length(palabras[i]) + colorSize);
                    resultado.add(resto);
                }
            } else {
                resultado.add(palabras[i]);
            }
        }
        return (String[]) resultado.toArray(new String[0]);
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  Bloque estático de inicialización
    // ─────────────────────────────────────────────────────────────────────────
    static {
        scanner      = new Scanner(System.in);
        inicio       = 0L;
        finaly       = 0L;
        tiempo       = 0L;
        hasEscapeS   = false;
        colorSize    = 0;
        colorSafe    = "";
        ansiExpresion = Pattern.compile("\\e\\[(\\d)+(;(\\d)+)*m");
    }
}