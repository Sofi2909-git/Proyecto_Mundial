package proyectomundial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.apache.commons.lang3.StringUtils;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 * Creador: Ing. Miguel Ropero - Profesor de Universidad Simon Bolivar
 * Modificador: Sofi Galvis - Estudiante de Universidad Simon Bolivar
 */
public class GUIManual extends JFrame {

    // Matriz que permite almancenar la información de las selecciones futbol cargadas
    public String[][] selecciones = null;

    // Matriz que permite almacenar los resultado de los partidos cargardos
    public String[][] resultados = null;

    //Matriz usada en la clasificacion de equipos
    private String[] equipos = null;

    // Elementos de bara Lateral
    private JPanel jPanelLeft;
    private JPanel jPanelIconFIFA;
    private JLabel iconFIFA;

    // Elementos de opciones de Menú
    private JPanel jPanelMenu;

    private JPanel jPanelMenuHome;
    private JLabel btnHome;

    private JPanel jPanelMenuSelecciones;
    private JLabel btnSelecciones;

    private JPanel jPanelMenuResultados;
    private JLabel btnResultados;

    private JPanel jPanelMenuDashboardSel;
    private JLabel btnDashboardSel;

    private JPanel jPanelMenuDashboardRes;
    private JLabel btnDashboardRes;

    private JPanel jPanelMenuNuevo;
    private JLabel btnMenuNuevo;

    // Elementos de panel de contenido
    private JPanel jPanelRight;
    private JPanel jPanelLabelTop;
    private JLabel jLabelTop;

    private JPanel jPanelMain;

    //Elementos para mostrar información en los dash
    private JPanel panelDash;
    private JTable table;
    private JScrollPane scrollPane;

    //Variables globales para el parcial 
    //Matriz usada para la nueva opcion de menu
    private final String[][] matrizAuditoria = new String[5][2];
    private final int contadorAuditoria[] = new int[5];

    public GUIManual() {

        // Se inician los componentes gráficos
        initComponents();

        // Se configuran propiedades de nuestra Ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Se llama la función home para que al momento de iniciar la aplicacoón, por defecto se muestre el home
        accionHome();

    }

    private void initComponents() {

        // Inicializamos componentes del Menu Lateral
        jPanelLeft = new JPanel();

        jPanelIconFIFA = new JPanel();
        iconFIFA = new JLabel();
        jPanelMenu = new JPanel();

        jPanelMenuHome = new JPanel();
        btnHome = new JLabel();

        jPanelMenuSelecciones = new JPanel();
        btnSelecciones = new JLabel();

        jPanelMenuResultados = new JPanel();
        btnResultados = new JLabel();

        jPanelMenuDashboardSel = new JPanel();
        btnDashboardSel = new JLabel();

        jPanelMenuDashboardRes = new JPanel();
        btnDashboardRes = new JLabel();

        jPanelMenuNuevo = new JPanel();
        btnMenuNuevo = new JLabel();

        //Elementos para mostrar información en los dash
        panelDash = new JPanel();

        // Pinta el logo de la aplicación
        pintarLogo();

        // Pinta la opción de menú del Home
        pintarMenuHome();

        // Pinta la opción de Menú de las Selecciones
        pintarMenuSelecciones();

        // Pinta la opción de Menú de los resultado
        pintarMenuResultados();

        // Pinta la opción de Menú del dashboard de equipo
        pintarMenuDashboardSel();

        // Pinta la opción de Menú del dahboard de resultado
        pintarMenuDashboardRes();

        //Pintar opcion nueva con matriz aleatoria
        auditoria();

        // Pinta y ajuste diseño del contenedor del panel izquierdo
        pintarPanelIzquierdo();

        // Inicializa los componentes del panel derecho de los contenidos
        jPanelRight = new JPanel();
        jPanelLabelTop = new JPanel();
        jPanelMain = new JPanel();

        // Pinta la barra superrior de color azul claro, del panel de contenido
        pintarLabelTop();

        // Pinta y ajusta diseño del contenedor de contenidos
        pintarPanelDerecho();

        setTitle("Mundial");
        pack();
        setVisible(true);
    }

    private void pintarLogo() {
        jPanelIconFIFA.add(iconFIFA);
        jPanelIconFIFA.setOpaque(false);
        jPanelIconFIFA.setPreferredSize((new java.awt.Dimension(220, 80)));
        jPanelIconFIFA.setMaximumSize(jPanelIconFIFA.getPreferredSize());
        iconFIFA.setIcon(new ImageIcon(getClass().getResource("/resources/Easports_fifa_logo.svg.png")));
        jPanelLeft.add(jPanelIconFIFA, BorderLayout.LINE_START);

    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación del HOME Define estilos, etiquetas, iconos que
     * decoran la opción del Menú. Esta opción de Menu permite mostrar la página
     * de bienvenida de la aplicación
     */
    private void pintarMenuHome() {
        btnHome.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioHome = new JLabel();
        jPanelMenuHome.setBackground(new java.awt.Color(0, 0, 0));
        jPanelMenuHome.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuHome.setLayout(new BorderLayout(15, 0));
        jPanelMenuHome.add(vacioHome, BorderLayout.WEST);
        jPanelMenuHome.add(btnHome, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuHome);

        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //Modificacion para el parcial
                contadorAuditoria[0] += 1;
                accionHome();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hacer click sobre la opción de
     * navegación Home Permite modificar la etiqueta de Navegación en Home,
     * remover los elementos que hay en el panel de contenidos y agregar la
     * imagen de inicio de la aplicación
     */
    private void accionHome() {
        jLabelTop.setText("Home");

        jPanelMain.removeAll();
        JPanel homePanel = new JPanel();
        JLabel imageHome = new JLabel();

        imageHome.setIcon(new ImageIcon(getClass().getResource("/resources/fifaHome.jpg")));
        imageHome.setPreferredSize(new java.awt.Dimension(700, 400));
        homePanel.add(imageHome);

        jPanelMain.add(homePanel, BorderLayout.CENTER);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de SELECCIONES Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar las
     * selecciones de futbol cargadas en la aplicación
     */
    private void pintarMenuSelecciones() {
        btnSelecciones.setIcon(new ImageIcon(getClass().getResource("/resources/icons/selecciones.png"))); // NOI18N
        btnSelecciones.setText("Selecciones");
        btnSelecciones.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioSelecciones = new JLabel();
        jPanelMenuSelecciones.setBackground(new java.awt.Color(0, 0, 0));
        jPanelMenuSelecciones.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuSelecciones.setLayout(new BorderLayout(15, 0));
        jPanelMenuSelecciones.add(vacioSelecciones, BorderLayout.WEST);
        jPanelMenuSelecciones.add(btnSelecciones, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuSelecciones);

        btnSelecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //Modificacion para el parcial
                contadorAuditoria[1] += 1;
                accionSelecciones();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Selecciones Permite ver la lista de selecciones que se
     * encuentran cargadas en la aplicación. Si la lista de selecciones en
     * vacía, muestra un botón que permite cargar un archivo CSV con la
     * información de las selelecciones
     */
    private void accionSelecciones() {
        jLabelTop.setText("Selecciones");
        // Si no hay selecciones cargadas, muestra el botón de carga de selecciones
        if (selecciones == null) {
            jPanelMain.removeAll();
            JPanel seleccionesPanel = new JPanel();
            JLabel notSelecciones = new JLabel();
            notSelecciones.setText("No hay selecciones cargadas, por favor cargue selecciones \n\n");
            seleccionesPanel.add(notSelecciones);

            JButton cargarFile = new JButton();
            cargarFile.setText("Seleccione el archivo");
            seleccionesPanel.add(cargarFile);
            cargarFile.addActionListener((ActionEvent evt) -> {
                cargarFileSelecciones();
            });

            jPanelMain.add(seleccionesPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay selecciones cargadas, llama el método que permite pintar la tabla de selecciones
        else {
            pintarTablaSelecciones(selecciones);
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de RESULTADOS Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar los
     * diferentes resultados de los partidos de la fase de grupos de un mundial
     */
    private void pintarMenuResultados() {
        btnResultados.setIcon(new ImageIcon(getClass().getResource("/resources/icons/resultados.png"))); // NOI18N
        btnResultados.setText("Resultados");
        btnResultados.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioResultados = new JLabel();
        jPanelMenuResultados.setBackground(new java.awt.Color(0, 0, 0));
        jPanelMenuResultados.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuResultados.setLayout(new BorderLayout(15, 0));
        jPanelMenuResultados.add(vacioResultados, BorderLayout.WEST);
        jPanelMenuResultados.add(btnResultados, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuResultados);

        btnResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contadorAuditoria[2] += 1;
                accionResultados();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Resultados Permite ver la lista de resultados que se
     * encuentran cargadas en la aplicación. Si la lista de resultados en vacía,
     * muestra un botón que permite cargar un archivo CSV con la información de
     * los resultados
     */
    private void accionResultados() {
        jLabelTop.setText("Resultados");

        // Si no hay resultado cargados, muestra el botón de carga de resultado
        if (resultados == null) {
            jPanelMain.removeAll();
            JPanel resultadosPanel = new JPanel();

            JLabel notResultados = new JLabel();
            notResultados.setText("No hay resultados, por favor cargue resultados \n\n");
            resultadosPanel.add(notResultados);

            JButton cargarFile = new JButton();
            cargarFile.setText("Seleccione el archivo");
            resultadosPanel.add(cargarFile);
            cargarFile.addActionListener((ActionEvent evt) -> {
                cargarFileResultados();
            });

            jPanelMain.add(resultadosPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay ressultados cargados, llama el método que permite pintar la tabla de resultado
        else {
            pintarTablaResultados(resultados);
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Selecciones Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de las selecciones de futbol que fueron cargadas
     */
    private void pintarMenuDashboardSel() {
        btnDashboardSel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnDashboardSel.setText("Dash Selecciones");
        btnDashboardSel.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuDashboardSel.setBackground(new java.awt.Color(0, 0, 0));
        jPanelMenuDashboardSel.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardSel.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardSel.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuDashboardSel.add(btnDashboardSel, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardSel);

        btnDashboardSel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contadorAuditoria[3] += 1;
                accionDashboardSel();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardSel() {
        jLabelTop.setText("Dash Selecciones");
        //Tipo de letra
        Font fontTitulos = new Font("Times New Roman", Font.BOLD, 16);
        Font font = new Font("Times New Roman", Font.BOLD, 14);
        //Refrescar
        jPanelMain.removeAll();
        panelDash.removeAll();
        //Verificar si esta cargada la información
        if (selecciones == null) {
            JLabel notSelecciones = new JLabel();
            notSelecciones.setText("Cargue la información en selecciones para conocer más detalles \n\n");
            notSelecciones.setFont(font);
            panelDash.add(notSelecciones);
        } else {
            //Creacion del panel
            panelDash.setLayout(new BoxLayout(panelDash, BoxLayout.Y_AXIS));
            panelDash.setPreferredSize((new java.awt.Dimension(620, 410)));
            panelDash.setMaximumSize(jPanelRight.getSize());

            //Creacion del menu
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("Presione para conocer las opciones");

            //Creación de las opciones de menú
            JMenuItem menuItem1 = new JMenuItem("Total de selecciones cargadas");
            JMenuItem menuItem2 = new JMenuItem("Número de selecciones por continente");
            JMenuItem menuItem3 = new JMenuItem("Cantidad de nacionalidades diferentes de los directores técnicos");
            JMenuItem menuItem4 = new JMenuItem("Raking de nacionalidades de los directores técnicos");

            //Diseño de menu
            menuItem1.setFont(font);
            menuItem2.setFont(font);
            menuItem3.setFont(font);
            menuItem4.setFont(font);

            //Añadirlo al menu principal
            menu.add(menuItem1);
            menu.add(menuItem2);
            menu.add(menuItem3);
            menu.add(menuItem4);
            menu.setFont(fontTitulos);
            menuBar.add(menu);

            //creacion de panel de menu
            JPanel form = new JPanel();
            form.setLayout(new GridLayout(2, 1, 0, 0));
            form.add(menuBar);
            table = new JTable();
            scrollPane = new JScrollPane(table);
            panelDash.add(form);
            panelDash.add(scrollPane);

            //1. Total de selecciones cargadas
            menuItem1.addActionListener((ActionEvent e) -> {
                totalSelecciones();
            });

            //2. Numero de selecciones por continente
            menuItem2.addActionListener((ActionEvent e) -> {
                seleccionesContinente();
            });

            //3. Cantidad de continentes diferentes de los directores técnicos
            menuItem3.addActionListener((ActionEvent e) -> {
                cantidadNacionalidades();
            });

            //4. Raking de continentes de los directores técnicos
            menuItem4.addActionListener((ActionEvent e) -> {
                rankingNacionalidades();
            });
        }

        jPanelMain.removeAll();
        jPanelMain.add(panelDash, BorderLayout.AFTER_LINE_ENDS);
        jPanelMain.repaint();
        jPanelMain.revalidate();

    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Resultados Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de los resultados de los partidos que fueron cargados
     */
    private void pintarMenuDashboardRes() {
        btnDashboardRes.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnDashboardRes.setText("Dash Resultados");
        btnDashboardRes.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardResultados = new JLabel();
        jPanelMenuDashboardRes.setBackground(new java.awt.Color(0, 0, 0));
        jPanelMenuDashboardRes.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardRes.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardRes.add(vacioDashboardResultados, BorderLayout.WEST);
        jPanelMenuDashboardRes.add(btnDashboardRes, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardRes);

        btnDashboardRes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contadorAuditoria[4] += 1;
                accionDashboardRes();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardRes() {
        jLabelTop.setText("Dash Resultados");
        //Tipo de letra
        Font fontTitulos = new Font("Times New Roman", Font.BOLD, 16);
        Font font = new Font("Times New Roman", Font.BOLD, 14);
        //Refrescar
        jPanelMain.removeAll();
        panelDash.removeAll();
        //Verificar si esta cargada la información 
        if (resultados == null) {
            JLabel notResultados = new JLabel();
            notResultados.setText("Cargue la información en resultados para conocer más detalles \n\n");
            notResultados.setFont(font);
            panelDash.add(notResultados);
        } else {
            //Creacion del panel
            panelDash.setLayout(new BoxLayout(panelDash, BoxLayout.Y_AXIS));
            panelDash.setPreferredSize((new java.awt.Dimension(620, 410)));
            panelDash.setMaximumSize(jPanelRight.getPreferredSize());
            //Creacion del menu
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("Presione para conocer las opciones");
            //Creacion de opciones de menu
            JMenuItem menuItem1 = new JMenuItem("Número de partidos cargados ");
            JMenuItem menuItem2 = new JMenuItem("Promedio de goles por partido");
            JMenuItem menuItem3 = new JMenuItem("Partido con más goles y partido con menos goles");
            JMenuItem menuItem4 = new JMenuItem("Número de partidos dónde hubo un ganador y número de partidos dónde hubo empate");
            JMenuItem menuItem5 = new JMenuItem("Selcción o selecciones con más goles y con menos goles");
            JMenuItem menuItem6 = new JMenuItem("Selección con más puntos y menos puntos");
            JMenuItem menuItem7 = new JMenuItem("Continente o continentes con más goles y menos goles");
            JMenuItem menuItem8 = new JMenuItem("Clasificados por cada grupo (Clasifican los dos primeros equipos de cada grupo)");

            //Diseño de las opciones de menu
            menuItem1.setFont(font);
            menuItem2.setFont(font);
            menuItem3.setFont(font);
            menuItem4.setFont(font);
            menuItem5.setFont(font);
            menuItem6.setFont(font);
            menuItem7.setFont(font);
            menuItem8.setFont(font);

            //Añadir opciones al menu
            menu.add(menuItem1);
            menu.add(menuItem2);
            menu.add(menuItem3);
            menu.add(menuItem4);
            menu.add(menuItem5);
            menu.add(menuItem6);
            menu.add(menuItem7);
            menu.add(menuItem8);
            menu.setFont(fontTitulos);
            menuBar.add(menu);

            //creacion de panel de menu
            JPanel form = new JPanel();
            form.setLayout(new GridLayout(2, 1, 0, 0));
            form.add(menuBar);
            table = new JTable();
            scrollPane = new JScrollPane(table);
            panelDash.add(form);
            panelDash.add(scrollPane);

            //1.Número de partidos cargados
            menuItem1.addActionListener((ActionEvent e) -> {
                numeroPartidos();
            });

            //2. Promedio de goles
            menuItem2.addActionListener((ActionEvent e) -> {
                promedioGoles();
            });

            //3. Partidos con mas y menos goles
            menuItem3.addActionListener((ActionEvent e) -> {
                maxMinGoles();
            });

            //4. Partidos con ganadores y con empates
            menuItem4.addActionListener((ActionEvent e) -> {
                contarGanadoresYEmpates();
            });

            //5. Seleccion con mayor y menor goles
            menuItem5.addActionListener((ActionEvent e) -> {
                maxMinGolesEquipos();
            });

            //6. Selección con más puntos y menos puntos
            menuItem6.addActionListener((ActionEvent e) -> {
                maxMinPuntosEquipos();
            });

            //7. Continente o continentes con más goles y menos goles
            menuItem7.addActionListener((ActionEvent e) -> {
                maxMinGolesContinentes();
            });

            //8. Clasificados por cada grupo (Clasifican los dos primeros equipos de cada grupo)
            menuItem8.addActionListener((ActionEvent e) -> {
                clasificarPorGrupo();
            });

        }

        jPanelMain.removeAll();
        jPanelMain.add(panelDash, BorderLayout.AFTER_LINE_ENDS);
        jPanelMain.repaint();
        jPanelMain.revalidate();

    }

    /**
     * Metodo que pinta la opcion de menu con nombre Auditoria, dispuesta para
     * el segundo parcial BY: Sofi Galvis
     */
    private void auditoria() {
        btnMenuNuevo.setIcon(new ImageIcon(getClass().getResource("/resources/icons/logoGatito.png")));
        btnMenuNuevo.setText("Auditoria");
        btnMenuNuevo.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioMenuNuevo = new JLabel();
        jPanelMenuNuevo.setBackground(new java.awt.Color(0, 0, 0));
        jPanelMenuNuevo.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuNuevo.setLayout(new BorderLayout(15, 0));
        jPanelMenuNuevo.add(vacioMenuNuevo, BorderLayout.WEST);
        jPanelMenuNuevo.add(btnMenuNuevo, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuNuevo);

        btnMenuNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Auditoria");
                accionAuditoria();
            }
        });
    }

    /**
     * Metodo que llama la matriz con la informacion
     */
    private void accionAuditoria() {
        jLabelTop.setText("Auditoria");
        //Llenar Matriz Nueva
        LlenarMatriz();
        //Pintar la tabla 
        pintarMatriz();
    }

    /**
     * LLenado de la matriz que controla la cantidad de veces que se navega por
     * cada menu
     */
    private void LlenarMatriz() {
        String nombresAuditoria[] = {"Home", "Selecciones", "Resultados", "Dash Selecciones", "Dash Resultado"};
        for (int i = 0; i < matrizAuditoria.length; i++) {
            matrizAuditoria[i][0] = nombresAuditoria[i];
            matrizAuditoria[i][1] = String.valueOf(contadorAuditoria[i]);
        }
    }

    /**
     * Metodo que pinta la matriz de la opcion Auditoria
     */
    private void pintarMatriz() {
        String[] columnNames = {"Opciones", "Cantidad de visitas"};
        Font font = new Font("Time New Roman", Font.PLAIN, 14);
        JTable table = new JTable(matrizAuditoria, columnNames);
        table.setRowHeight(30);
        table.setFont(font);
        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();

    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte izquierda de la interfaz, dónde se visulaiza el
     * menú de navegaación
     */
    private void pintarPanelIzquierdo() {
        // Se elimina el color de fondo del panel del menú
        jPanelMenu.setOpaque(false);

        // Se agrega un border izquierdo, de color blanco para diferenciar el panel de menú del panel de contenido
        jPanelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));

        // Se define un BoxLayot de manera vertical para los elementos del panel izquierdo
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelLeft.setBackground(new java.awt.Color(15, 15, 15));
        getContentPane().add(jPanelLeft, java.awt.BorderLayout.LINE_START);
        jPanelLeft.add(jPanelMenu);
        jPanelLeft.setPreferredSize((new java.awt.Dimension(220, 540)));
        jPanelLeft.setMaximumSize(jPanelLeft.getPreferredSize());
    }

    /**
     * Función que permite leer un archivo y procesar el contenido que tiene en
     * cada una de sus líneas El contenido del archivo es procesado y cargado en
     * la matriz de selecciones. Una vez la información se carga en la atriz, se
     * hace un llamado a la función pintarTablaSelecciones() que se encarga de
     * pintar en la interfaz una tabla con la información almacenada en la
     * matriz de selecciones
     */
    public void cargarFileSelecciones() {
        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {

            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Se define las dimensiones de la matriz de selecciones
            selecciones = new String[32][5];

            // Permite que el sistema se salte la léctura de los encabzados del archivo CSV
            entrada.nextLine();

            int i = 0;
            // Se leen cada unas de las líneas del archivo
            while (entrada.hasNext()) {
                System.out.println(i);
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                System.arraycopy(columns, 0, selecciones[i], 0, columns.length);
                i++;
            }

            pintarTablaSelecciones(selecciones);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pinta la tabla con la información de las
     * selelceciones que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"ID","Selección", "Continente",
     * "DT", "Nacionalidad DT"} Columnas que se corresponden son la información
     * que fue leida desde el archivo csv
     *
     * @param elementos
     */
    public void pintarTablaSelecciones(String[][] elementos) {
        String[] columnNames = {"ID", "Selección", "Continente", "DT", "Nacionalidad DT"};
        JTable table = new JTable(elementos, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Equipos");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();

        //Si presionar el boton buscar
        buscar.addActionListener((ActionEvent evt) -> {
            filtrarBusquedas(field, selecciones, 1);
        });

        limpiar.addActionListener((ActionEvent evt) -> {
            System.out.println("Entra");
            limpiar(1);

        });
    }

    /**
     * Función que tiene la lógica que permite leer un archivo CSV de resultados
     * y cargarlo sobre la matriz resultados que se tiene definida cómo variable
     * global. Luego de cargar los datos en la matriz, se llama la función
     * pintarTablaResultados() que se encarga de visulizar el contenido de la
     * matriz en un componente gráfico de tabla
     */
    public void cargarFileResultados() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {
            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Se define las dimensiones de la matriz de selecciones
            resultados = new String[48][7];
            entrada.nextLine();

            int i = 0;
            // Se iteran cada una de las líneas del archivo
            while (entrada.hasNext()) {
                System.out.println(i);
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                System.arraycopy(columns, 0, resultados[i], 0, columns.length);
                i++;
            }

            pintarTablaResultados(resultados);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pintar la tabla con la información de los
     * resultado que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"Grupo","Local", "Visitante",
     * "Continente L", "Continente V", "Goles L", "Goles V"} Columnas que se
     * corresponden son la información que fue leida desde el archivo csv
     *
     * @param elementos
     */
    public void pintarTablaResultados(String[][] elementos) {
        String[] columnNames = {"Grupo", "Local", "Visitante", "Continente L", "Continente V", "Goles L", "Goles V"};
        JTable table = new JTable(elementos, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Resultados");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();

        //Si presionar el boton buscar
        buscar.addActionListener((ActionEvent evt) -> {
            filtrarBusquedas(field, resultados, 2);
        });

        limpiar.addActionListener((ActionEvent evt) -> {
            limpiar(2);
        });
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte derecha de la interfaz, dónde se visulaiza de
     * manera dinámica el contenido de cada una de las funciones que puede
     * realizar el usuario sobre la aplicación.
     */
    private void pintarPanelDerecho() {

        // Define las dimensiones del panel
        jPanelMain.setPreferredSize((new java.awt.Dimension(620, 420)));
        jPanelMain.setMaximumSize(jPanelLabelTop.getPreferredSize());

        getContentPane().add(jPanelRight, java.awt.BorderLayout.CENTER);
        jPanelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        jPanelRight.add(jPanelLabelTop, BorderLayout.LINE_START);
        jPanelRight.add(jPanelMain);
        jPanelRight.setPreferredSize((new java.awt.Dimension(620, 540)));
        jPanelRight.setMaximumSize(jPanelRight.getPreferredSize());
    }

    /**
     * Función que permite pinta la barra azul del contenedor de contenidos.
     * Barra azul que permite indicar en que sección que se encuentra navegando
     * el usuario.
     */
    private void pintarLabelTop() {
        jLabelTop = new JLabel();
        jLabelTop.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabelTop.setForeground(new java.awt.Color(241, 241, 241));
        jLabelTop.setText("Home");

        JLabel vacioTopLabel = new JLabel();
        jPanelLabelTop.setLayout(new BorderLayout(15, 0));
        jPanelLabelTop.add(vacioTopLabel, BorderLayout.WEST);
        jPanelLabelTop.setBackground(new java.awt.Color(0, 0, 0));
        jPanelLabelTop.add(jLabelTop, BorderLayout.CENTER);
        jPanelLabelTop.setPreferredSize((new java.awt.Dimension(620, 120)));
        jPanelLabelTop.setMaximumSize(jPanelLabelTop.getPreferredSize());
    }

    /**
     * El metodo filtrar busquedas realiza el filtrado de datos de acuerdo a lo
     * que el usuario ingrese desde la interfaz
     *
     * @param field
     * @param elementos
     */
    private void filtrarBusquedas(JTextField field, String[][] elementos, int ind) {
        //Variables
        int filas = elementos.length;
        int columnas = elementos[0].length;
        int m = 0;
        String texto = StringUtils.stripAccents(field.getText().toLowerCase()); // Convertimos a minúsculas y eliminamos tildes
        if (!texto.isEmpty()) {
            String[][] filtroSeleccion = new String[filas][columnas];
            for (int i = 0; i < filas; i++) {
                boolean contieneTexto = false;
                for (int j = 0; j < columnas; j++) {
                    String elemento = StringUtils.stripAccents(elementos[i][j].toLowerCase()); // Convertimos a minúsculas y eliminamos tildes
                    if (elemento.contains(texto)) {
                        contieneTexto = true;
                        break;
                    }
                }
                if (contieneTexto) {
                    System.arraycopy(elementos[i], 0, filtroSeleccion[m], 0, columnas);
                    m++;
                }
            }
            // Redimensionamos la matriz nueva
            String[][] matrizRedimensionada = new String[m][columnas];
            for (int i = 0; i < m; i++) {
                System.arraycopy(filtroSeleccion[i], 0, matrizRedimensionada[i], 0, columnas);
            }
            // Imprimimos la matriz redimensionada
            if (ind == 1) {
                pintarTablaSelecciones(matrizRedimensionada);
            } else {
                pintarTablaResultados(matrizRedimensionada);
            }
        }
    }

    /**
     * Este metodo carga de nuevo la informacion de la matriz sin filtros
     *
     * @param ind
     */
    private void limpiar(int ind) {
        if (ind == 1) {
            pintarTablaSelecciones(selecciones);
        } else {
            pintarTablaResultados(resultados);
        }
    }

    private void totalSelecciones() {
        String cantidad = String.valueOf(selecciones.length);
        String matriz[][] = {{cantidad}};
        String columnNames[] = {"Total de selecciones"};
        mostrar(matriz, columnNames);
    }

    /**
     * Este metodo realiza el conteo de equipos por cada continente.
     *
     */
    private void seleccionesContinente() {
        String[] continentes = new String[7];
        String[] columnNames = {"Continentes", "Cantidad de equipos"};
        int[] resultado = new int[7];
        String[][] resultadoStr = new String[7][2];
        int cantidadContinente = 0;

        for (String[] seleccione : selecciones) {
            String continente = seleccione[2];
            boolean repetido = false;
            for (int j = 0; j < cantidadContinente; j++) {
                if (continentes[j].equals(continente)) {
                    resultado[j]++;
                    repetido = true;
                    break;
                }
            }
            if (!repetido) {
                continentes[cantidadContinente] = continente;
                resultado[cantidadContinente]++;
                cantidadContinente++;
            }
        }
        for (int i = 0; i < cantidadContinente; i++) {
            resultadoStr[i][0] = continentes[i];
            resultadoStr[i][1] = String.valueOf(resultado[i]);
        }

        mostrar(resultadoStr, columnNames);
    }

    /**
     * Este metodo obtiene la cantidad total de las diferentes nacionalidades de
     * los directores tecnicos
     */
    private void cantidadNacionalidades() {
        String[] nacionalidades = new String[32];
        int cantidad = 0;
        String[] columnNames = {"Cantidad de diferentes directores técnicos"};

        for (int i = 0; i < selecciones.length; i++) {
            String nacionalidad = selecciones[i][4];
            boolean repetida = false;

            // Verificar si la nacionalidad ya se encuentra en el arreglo
            for (int j = 0; j < cantidad; j++) {
                if (nacionalidades[j].equals(nacionalidad)) {
                    repetida = true;
                    break;
                }
            }
            if (!repetida) {
                nacionalidades[cantidad] = nacionalidad;
                cantidad++;
            }
        }
        String matriz[][] = {{String.valueOf(cantidad)}};
        mostrar(matriz, columnNames);
    }

    /**
     * Este metodo calcula el ranking de los diferentes directores técnicos por
     * continentes
     */
    private void rankingNacionalidades() {
        String[] nacionalidades = new String[32];
        int[] cantidades = new int[32];
        int cantidadNacionalidadesDiferentes = 0;
        String[] columnNames = {"Continente de origen", "Número de directores técnicos"};

        for (String[] seleccione : selecciones) {
            String nacionalidad = seleccione[4];
            boolean nacionalidadYaContada = false;
            for (int j = 0; j < cantidadNacionalidadesDiferentes; j++) {
                if (nacionalidades[j].equals(nacionalidad)) {
                    cantidades[j]++;
                    nacionalidadYaContada = true;
                    break;
                }
            }
            if (!nacionalidadYaContada) {
                nacionalidades[cantidadNacionalidadesDiferentes] = nacionalidad;
                cantidades[cantidadNacionalidadesDiferentes] = 1;
                cantidadNacionalidadesDiferentes++;
            }
        }
        // Ordenar los arreglos en orden descendente de las cantidades
        for (int i = 0; i < cantidadNacionalidadesDiferentes; i++) {
            for (int j = i + 1; j < cantidadNacionalidadesDiferentes; j++) {
                if (cantidades[j] > cantidades[i]) {
                    String tmpNacionalidad = nacionalidades[i];
                    int tmpCantidad = cantidades[i];
                    nacionalidades[i] = nacionalidades[j];
                    cantidades[i] = cantidades[j];
                    nacionalidades[j] = tmpNacionalidad;
                    cantidades[j] = tmpCantidad;
                }
            }
        }

        String[][] rankingDeNacionalidad = new String[cantidadNacionalidadesDiferentes][2];
        for (int i = 0; i < cantidadNacionalidadesDiferentes; i++) {
            rankingDeNacionalidad[i][0] = nacionalidades[i];
            rankingDeNacionalidad[i][1] = String.valueOf(cantidades[i]);
        }
        mostrar(rankingDeNacionalidad, columnNames);
    }

    /**
     * Este metodo obtiene el total de partidos de la matriz resultados
     */
    private void numeroPartidos() {
        String cantidad = String.valueOf(resultados.length);
        String matriz[][] = {{cantidad}};
        String columnNames[] = {"Total de partidos cargados: "};
        mostrar(matriz, columnNames);
    }

    /**
     * Este metodo calcula el promedio de goles por partido
     */
    private void promedioGoles() {
        int numPartidos = resultados.length;
        int sumaGoles = 0;

        for (int i = 0; i < numPartidos; i++) {
            int golesLocal = Integer.parseInt(resultados[i][5]);
            int golesVisitante = Integer.parseInt(resultados[i][6]);
            sumaGoles += golesLocal + golesVisitante;
        }
        double promedioGoles = (double) sumaGoles / numPartidos;
        String promedio[][] = {{String.format("%.2f", promedioGoles)}};
        String columnNames[] = {"Promedio de goles: "};
        mostrar(promedio, columnNames);
    }

    /**
     * Este metodo calcula el max y minimo de goles de los partidos. Además
     * señala en que partido fueron
     */
    private void maxMinGoles() {
        // Variables de trabajo
        int numPartidos = resultados.length;
        int maxGoles = Integer.MIN_VALUE;
        int minGoles = Integer.MAX_VALUE;
        int partidoMaxGoles = 0;
        int partidoMinGoles = 0;
        int otroMaxPartido = 0;
        int otroMinPartido = 0;
        boolean otroMax = false;
        boolean otroMin = false;
        String[] columnNames = {"Numero de partido", "Cantidad de goles"};
        int cantidadMax = 1; // Inicialmente se asume que solo hay un partido con máximo goles
        int cantidadMin = 1; // Inicialmente se asume que solo hay un partido con mínimo goles

        // Encontrar el máximo y mínimo de goles por partido y determinar la cantidad de partidos máximos y mínimos
        for (int i = 0; i < numPartidos; i++) {
            int golesLocal = Integer.parseInt(resultados[i][5]);
            int golesVisitante = Integer.parseInt(resultados[i][6]);
            int totalGoles = golesLocal + golesVisitante;

            if (totalGoles > maxGoles) {
                maxGoles = totalGoles;
                partidoMaxGoles = i;
                cantidadMax = 1;
                otroMax = false;
            } else if (totalGoles == maxGoles) {
                if (!otroMax) {
                    otroMaxPartido = i;
                    cantidadMax++;
                    otroMax = true;
                } else {
                    cantidadMax++;
                }
            }

            if (totalGoles < minGoles) {
                minGoles = totalGoles;
                partidoMinGoles = i;
                cantidadMin = 1;
                otroMin = false;
            } else if (totalGoles == minGoles) {
                if (!otroMin) {
                    otroMinPartido = i;
                    cantidadMin++;
                    otroMin = true;
                } else {
                    cantidadMin++;
                }
            }
        }

        // Crear matriz dinámica para almacenar los resultados
        int cantidadTotal = cantidadMax + cantidadMin;
        String[][] resultadosStr = new String[cantidadTotal][2];

        // Llenar la matriz con la información de los partidos con máximo y mínimo goles
        int index = 0;
        for (int i = 0; i < numPartidos; i++) {
            int golesLocal = Integer.parseInt(resultados[i][5]);
            int golesVisitante = Integer.parseInt(resultados[i][6]);
            int totalGoles = golesLocal + golesVisitante;

            if (totalGoles == maxGoles || totalGoles == minGoles) {
                resultadosStr[index][0] = Integer.toString(i + 1);
                resultadosStr[index][1] = Integer.toString(totalGoles);
                index++;
            }
        }

        // Mostrar la información almacenada en resultadosStr utilizando el método mostrar
        mostrar(resultadosStr, columnNames);
    }

    /**
     * Cuenta el numero de partidos ganados y empatados en la matriz
     */
    private void contarGanadoresYEmpates() {
        int partidosGanados = 0;
        int partidosEmpatados = 0;
        String[] columnNames = {"Partidos donde hubo ganador", "Partidos donde hubo empate"};

        for (int i = 0; i < resultados.length; i++) {
            int golesLocal = Integer.parseInt(resultados[i][5]);
            int golesVisitante = Integer.parseInt(resultados[i][6]);

            if (golesLocal > golesVisitante || golesVisitante > golesLocal) {
                partidosGanados++;
            } else {
                partidosEmpatados++;
            }
        }
        String[][] resultadosStr = new String[1][2];
        resultadosStr[0][0] = String.valueOf(partidosGanados);
        resultadosStr[0][1] = String.valueOf(partidosEmpatados);
        mostrar(resultadosStr, columnNames);
    }

    /**
     * Encuentra el equipo con mas y con menos goles
     */
    private void maxMinGolesEquipos() {
        int numEquipos = 32;
        String[] equipos = new String[numEquipos];
        int[] totalesGoles = new int[numEquipos];
        String[] columnNames = {"Equipo con más goles", "Equipo con menos goles"};
        String[][] resultadosStr = new String[1][2];

        for (String[] resultado : resultados) {
            String local = resultado[1];
            String visitante = resultado[2];
            int golesLocal = Integer.parseInt(resultado[5]);
            int golesVisitante = Integer.parseInt(resultado[6]);
            int indiceLocal = encontrarIndicesMatriz(equipos, local);
            int indiceVisitante = encontrarIndicesMatriz(equipos, visitante);
            if (indiceLocal != -1) {
                totalesGoles[indiceLocal] += golesLocal;
            }
            if (indiceVisitante != -1) {
                totalesGoles[indiceVisitante] += golesVisitante;
            }
        }

        int indiceEquipoMaxGoles = 0;
        int indiceEquipoMinGoles = 0;
        for (int i = 1; i < totalesGoles.length; i++) {
            if (totalesGoles[i] > totalesGoles[indiceEquipoMaxGoles]) {
                indiceEquipoMaxGoles = i;
            }
            if (totalesGoles[i] < totalesGoles[indiceEquipoMinGoles]) {
                indiceEquipoMinGoles = i;
            }
        }
        resultadosStr[0][0] = String.valueOf(equipos[indiceEquipoMaxGoles]);
        resultadosStr[0][1] = String.valueOf(equipos[indiceEquipoMinGoles]);
        mostrar(resultadosStr, columnNames);

    }

    /**
     * Encuentra equipo con más y menos puntos
     */
    private void maxMinPuntosEquipos() {
        int numEquipos = 32;
        String[] equipos = new String[numEquipos];
        int[] totalesPuntos = new int[numEquipos];
        String[] columnNames = {"Selección con más puntos", "Seleccion con menos puntos"};
        String[][] resultadosStr = new String[1][2];

        for (int i = 0; i < resultados.length; i++) {
            // nombres de los equipos
            String local = resultados[i][1];
            String visitante = resultados[i][2];

            // goles de los equipos
            int golesLocal = Integer.parseInt(resultados[i][5]);
            int golesVisitante = Integer.parseInt(resultados[i][6]);

            // calculamos los puntos correspondientes
            int puntosLocal = 0;
            int puntosVisitante = 0;
            if (golesLocal > golesVisitante) {
                puntosLocal = 3;
            } else if (golesVisitante > golesLocal) {
                puntosVisitante = 3;
            } else {
                puntosLocal = 1;
                puntosVisitante = 1;
            }

            // encontramos los índices correspondientes en los arreglos
            int indiceLocal = encontrarIndicesMatriz(equipos, local);
            int indiceVisitante = encontrarIndicesMatriz(equipos, visitante);

            // actualizamos los totales de puntos correspondientes
            totalesPuntos[indiceLocal] += puntosLocal;
            totalesPuntos[indiceVisitante] += puntosVisitante;
        }

        // encontramos los índices del equipo con más y menos puntos
        int indiceEquipoMaxPuntos = 0;
        int indiceEquipoMinPuntos = 0;
        for (int i = 1; i < totalesPuntos.length; i++) {
            if (totalesPuntos[i] > totalesPuntos[indiceEquipoMaxPuntos]) {
                indiceEquipoMaxPuntos = i;
            }
            if (totalesPuntos[i] < totalesPuntos[indiceEquipoMinPuntos]) {
                indiceEquipoMinPuntos = i;
            }
        }
        resultadosStr[0][0] = String.valueOf(equipos[indiceEquipoMaxPuntos] + ": " + totalesPuntos[indiceEquipoMaxPuntos] + " puntos");
        resultadosStr[0][1] = String.valueOf(equipos[indiceEquipoMinPuntos] + ": " + totalesPuntos[indiceEquipoMinPuntos] + " puntos");
        mostrar(resultadosStr, columnNames);
    }

    /**
     * Encuentra coontinentes donde los equipos realizaron la mayor o menor
     * cantidad de goles
     */
    private void maxMinGolesContinentes() {
        int tamaño = resultados.length;
        int numContinentes = 6;
        String[] continentes = new String[numContinentes];
        int[] totalesGoles = new int[numContinentes];
        String[] columnNames = {"Continente con más goles", "Continente con menos goles"};
        String[][] resultadosStr = new String[1][2];

        for (int i = 0; i < tamaño; i++) {
            // continentes de los equipos
            String continenteLocal = resultados[i][3];
            String continenteVisitante = resultados[i][4];

            // goles de los equipos
            int golesLocal = Integer.parseInt(resultados[i][5]);
            int golesVisitante = Integer.parseInt(resultados[i][6]);

            // encontramos los índices correspondientes en los arreglos
            int indiceContinenteLocal = encontrarIndicesMatriz(continentes, continenteLocal);
            int indiceContinenteVisitante = encontrarIndicesMatriz(continentes, continenteVisitante);

            // actualizamos los totales de goles correspondientes
            totalesGoles[indiceContinenteLocal] += golesLocal;
            totalesGoles[indiceContinenteVisitante] += golesVisitante;
        }

        // encontramos los índices del continente con más y menos goles
        int indiceContinenteMaxGoles = 0;
        int indiceContinenteMinGoles = 0;
        for (int i = 1; i < totalesGoles.length; i++) {
            if (totalesGoles[i] > totalesGoles[indiceContinenteMaxGoles]) {
                indiceContinenteMaxGoles = i;
            }
            if (totalesGoles[i] < totalesGoles[indiceContinenteMinGoles]) {
                indiceContinenteMinGoles = i;
            }
        }
        resultadosStr[0][0] = String.valueOf(continentes[indiceContinenteMaxGoles]);
        resultadosStr[0][1] = String.valueOf(continentes[indiceContinenteMinGoles]);
        mostrar(resultadosStr, columnNames);
    }

    /**
     * Encuentra un indice el arreglo con el nombre, si no está en el arreglo,
     * lo agrega y devuelve el índice correspondiente
     *
     * @param elemento
     * @param nombre
     * @return
     */
    private int encontrarIndicesMatriz(String[] elemento, String nombre) {
        for (int i = 0; i < elemento.length; i++) {
            if (elemento[i] == null) {
                elemento[i] = nombre;
                return i;
            } else if (elemento[i].equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metodo que inicia la clasificacion de los grupos
     */
    private void clasificarPorGrupo() {
        obtenerEquipos();
        String grupoActual = resultados[0][0];
        int cont = 1;

        for (String[] resultado : resultados) {
            String grupo = resultado[0];
            if (!grupo.equals(grupoActual)) {
                clasificarEquipos(grupoActual);
                grupoActual = grupo;
                cont = 1;
            }
            cont++;
        }

        clasificarEquipos(grupoActual);
    }

    /**
     * Obtiene el nombre de los equipos de cada grupo
     */
    private void obtenerEquipos() {
        String[] equiposTemp = new String[32];

        int index = 0;
        for (String[] resultado : resultados) {
            String equipo1 = resultado[1];
            String equipo2 = resultado[2];

            // Verificar si el equipo1 no se encuentra ya en el arreglo
            boolean equipo1Exist = false;
            for (int i = 0; i < index; i++) {
                if (equiposTemp[i].equals(equipo1)) {
                    equipo1Exist = true;
                    break;
                }
            }

            // Si no se encuentra, agregarlo al arreglo
            if (!equipo1Exist) {
                equiposTemp[index++] = equipo1;
            }

            // Verificar si el equipo2 no se encuentra ya en el arreglo
            boolean equipo2Exist = false;
            for (int i = 0; i < index; i++) {
                if (equiposTemp[i].equals(equipo2)) {
                    equipo2Exist = true;
                    break;
                }
            }

            // Si no se encuentra, agregarlo al arreglo
            if (!equipo2Exist) {
                equiposTemp[index++] = equipo2;
            }
        }

        // Crear el arreglo final de equipos sin elementos nulos
        equipos = new String[index];
        System.arraycopy(equiposTemp, 0, equipos, 0, index);
    }

    /**
     * Clasifica los equipos de acuerdo a las reglas establecidas por la FIFA
     *
     * @param grupo
     */
    private void clasificarEquipos(String grupo) {
        // Creamos un vector para almacenar los puntos de cada equipo en el grupo
        int[] puntos = new int[equipos.length];

        for (String[] resultado : resultados) {
            if (resultado[0].equals(grupo)) {
                String equipo1 = resultado[1];
                String equipo2 = resultado[2];
                int goles1 = Integer.parseInt(resultado[5]);
                int goles2 = Integer.parseInt(resultado[6]);
                // Actualizamos los puntos de cada equipo en el grupo
                if (goles1 > goles2) {
                    puntos[getIndex(equipo1)] += 3;
                } else if (goles1 < goles2) {
                    puntos[getIndex(equipo2)] += 3;
                } else {
                    puntos[getIndex(equipo1)] += 1;
                    puntos[getIndex(equipo2)] += 1;
                }
            }
        }
        // Obtenemos los equipos clasificados
        int max1 = -1, max2 = -1, clasificado1 = -1, clasificado2 = -1;
        for (int i = 0; i < puntos.length; i++) {
            if (puntos[i] > max1) {
                max2 = max1;
                clasificado2 = clasificado1;
                max1 = puntos[i];
                clasificado1 = i;
            } else if (puntos[i] > max2) {
                max2 = puntos[i];
                clasificado2 = i;
            }
        }
        String equipoClasificado1 = getNombreEquipo(clasificado1);
        String equipoClasificado2 = getNombreEquipo(clasificado2);

        // Imprimimos los equipos clasificados
        System.out.println("Equipos clasificados del grupo " + grupo + ": " + equipoClasificado1 + " y " + equipoClasificado2);
    }

    /**
     * Obtiene el indice en el arreglo de equipos para conocer el nombre
     *
     * @param equipo
     * @return
     */
    private int getIndex(String equipo) {
        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i].equals(equipo)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Obtenemos el nombre completo del equipo en base a su índice
     *
     * @param index
     * @return
     */
    private String getNombreEquipo(int index) {
        return equipos[index];
    }

    /**
     * Muestra en las opciones del dash de selecciones y el dash de resultados
     * la informacion de las opciones
     *
     * @param matriz
     * @param columnNames
     */
    private void mostrar(String[][] matriz, String[] columnNames) {
        if (table == null) {
            table = new JTable(matriz, columnNames);
            scrollPane = new JScrollPane(table);
            panelDash.add(scrollPane);
        } else {
            table.setModel(new DefaultTableModel(matriz, columnNames));
        }
        // Actualizar la interfaz gráfica
        table.setRowHeight(30);
        panelDash.revalidate();
        panelDash.repaint();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new GUIManual().setVisible(true);
        });
    }

}
