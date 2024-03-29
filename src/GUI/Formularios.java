package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Formularios extends JFrame {

    private int LayoutX;
    private int LayoutY;

    private int sizeXcont = 80;
    private int sizeYcont = 50;

    protected int sizeX;
    protected int sizeY;
    protected String titulo;
    protected ColoresFuentes grafica = new ColoresFuentes();

    private JPanel BordeSuperior = new JPanel();
    private JPanel contenedorBotones = new JPanel();
    private JLabel tituloFormulario = new JLabel();
    private JPanel contenedorIzquierto = new JPanel();

    public JLabel botonCerrar = new Etiquetas("", grafica.Rojo, Color.black, grafica.letraFuerte);
    public JLabel botonMinimizar = new Etiquetas("", grafica.Amarillo, Color.black, grafica.letraFuerte);
    public JLabel botonMaximizar = new Etiquetas("", grafica.Verde, Color.black, grafica.letraFuerte);


    public Formularios(int sizeX, int sizeY, String titulo) throws HeadlessException {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.titulo = titulo;
        this.setSize(sizeX, sizeY);
        this.setTitle(titulo);
        this.setUndecorated(true);
        this.setExtendedState(NORMAL);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        iniciarComponentesFormularios();
    }
    private void iniciarComponentesFormularios(){

        botonCerrar.setBounds(10, 14, 20, 20);
        botonMinimizar.setBounds( 35, 14, 20, 20);
        botonMaximizar.setBounds(60, 14,20,20);

        BordeSuperior.setBackground(grafica.Azul);
        BordeSuperior.setBorder(grafica.bordeNegro);
        BordeSuperior.setPreferredSize(new Dimension(sizeX,50));
        BordeSuperior.setLayout(new BorderLayout());

        contenedorBotones.setPreferredSize(new Dimension(sizeXcont,sizeYcont));
        contenedorBotones.setLayout(null);
        contenedorBotones.setBackground(null);

        contenedorBotones.add(botonCerrar);
        contenedorBotones.add(botonMinimizar);
        contenedorBotones.add(botonMaximizar);

        contenedorBotones.setLocation(0,0);

        contenedorIzquierto.setBackground(null);
        contenedorIzquierto.setPreferredSize(new Dimension(sizeXcont, sizeYcont));

        BordeSuperior.add(contenedorBotones, BorderLayout.LINE_START);
        BordeSuperior.add(contenedorIzquierto, BorderLayout.LINE_END);

        tituloFormulario.setText(titulo);
        tituloFormulario.setHorizontalAlignment(SwingConstants.CENTER);
        tituloFormulario.setVerticalAlignment(SwingConstants.CENTER);
        tituloFormulario.setFont(grafica.letraTitulos);
        tituloFormulario.setForeground(grafica.Blanco);

        BordeSuperior.add(tituloFormulario, BorderLayout.CENTER);
        this.getContentPane().add(BordeSuperior, BorderLayout.PAGE_START);


        eventosEntradaSalida(botonCerrar, "X");
        eventosEntradaSalida(botonMinimizar, "_");
        eventosEntradaSalida(botonMaximizar, "+");

        botonCerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                funcionBtnCerrar(e);
            }
        });

        botonMinimizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                funcionBtnMinimizar(e);
            }
        });

        botonMaximizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                funcionBtnMaximizar(e);
            }
        });

        BordeSuperior.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               bordeSuperiorMousePressed(e);
            }
        });

        BordeSuperior.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                bordeSuperiorMouseDragged(e);
            }
        });


    }

    private void eventosEntradaSalida(JLabel etiqueta, String textoMostrado){
        etiqueta.setFont(grafica.letraFuerte);
        etiqueta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                etiqueta.setText(textoMostrado);
                etiqueta.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e){
                super.mouseExited(e);
                etiqueta.setText("");
            }
        });
    }

    private void funcionBtnCerrar(MouseEvent e){
        this.dispose();
    }

    private void funcionBtnMinimizar(MouseEvent e){
        this.setExtendedState(ICONIFIED);
    }

    private void funcionBtnMaximizar(MouseEvent e){
        if(this.getExtendedState() == MAXIMIZED_BOTH){
            this.setExtendedState(NORMAL);
        } else{
            this.setExtendedState(MAXIMIZED_BOTH);
        }
    }

    private void bordeSuperiorMouseDragged(MouseEvent e){
        this.setLocation(e.getXOnScreen() - LayoutX, e.getYOnScreen() - LayoutY);
    }

    private void bordeSuperiorMousePressed(MouseEvent e){
        LayoutX = e.getX();
        LayoutY = e.getY();
    }

}

