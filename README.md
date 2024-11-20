import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import javax.swing.border.EmptyBorder; 

public class GUI extends JFrame {

    //paneles
    private JLayeredPane layeredPane; //Panel que permite superponer componentes (para el fondo)
    private JPanel panelImagenFondo; //Panel con la imagen de fondo del avion
    private JPanel panelFondo; //Panel donde van a estar todos los componentes (JButton, JTextField, etc)
    private JPanel panelIzquierdo; //Panel con información de reservas
    private JPanel panelDerecho; //Panel con grilla de asientos
    private JPanel panelButacas; //Panel de asientos
    private JPanel datosPanel; //Panel con información de reservas
    private JPanel topPanelSup; //Panel con dniInput 
    private JPanel topPanelInf; //Panel con botones Consultar y Reservar
    private JPanel topPanel; //Panel contenedor de topPanelSup y topPanelInf

    //botones
    private JButton [][] botones; //Botones para asientos
    private MiBoton botonConsultar,botonReservar;
    private MiBoton botonEliminarReserva, botonAceptar, botonAceptarReserva, botonCancelar;

    //textFields
    private JTextField InputDni;//Campo para que el usuario ingrese dni

    //labels y textField
    private JLabel etiquetaDni;
    private JLabel etiquetaNombre;
    private JLabel etiquetaApellido;
    private JLabel etiquetaCodTicket;
    private JLabel etiquetaUbicacion;

    private JTextField labelDni;
    private JTextField labelNombre;
    private JTextField labelApellido;
    private JTextField labelCodTicket;
    private JTextField labelUbicacion;

    //lógica
    private Logica logica;
    private int prevI = -1; // Índice de fila del botón previamente seleccionado
    private int prevJ = -1; // Índice de columna del botón previamente seleccionado

    //constructor
    public GUI(){         
        super("IPOO Check-in");
        logica = new Logica();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(600, 700));
        inicializarGUI();
        setVisible(true);
    }

    //metodo privado para inicializar las componentes     
    private void inicializarGUI() {

        // Creo el panel que permite superponer componentes (para el fondo)
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(600, 700));
        
        // Escalar la imagen al tamaño del panel
        ImageIcon imgPanel = new ImageIcon(getClass().getResource("avion_fondo.jpg"));
        Image imagen = imgPanel.getImage(); // Obtener la imagen del ImageIcon
        Image imagenEscalada = imagen.getScaledInstance(600, 700, Image.SCALE_SMOOTH); // Escalar la imagen
        ImageIcon imgEscalada = new ImageIcon(imagenEscalada); // Crear un nuevo ImageIcon con la imagen escalada

        // Crear el panel de fondo con la imagen
        panelImagenFondo = new JPanel();
        panelImagenFondo.setBounds(0, 0, 600, 700); // Ajustar tamaño y posición en el JLayeredPane
        JLabel lpImage = new JLabel(imgEscalada); // Asignar la imagen escalada al JLabel
        panelImagenFondo.add(lpImage);

        // Crear el panel de fondo, el cual va a tener los datos de las reservas y los asientos.
        panelFondo = new JPanel(new GridLayout(1, 2));
        panelFondo.setBounds(0, 0, 600, 700); // Mismo tamaño y posición que el fondo
        panelFondo.setOpaque(false); // Hacer transparente para ver el fondo
        
        //Panel con información de las reservas
        panelIzquierdo = new JPanel(new GridLayout(3,1));
        panelIzquierdo.setOpaque(false);
        panelIzquierdo.setBorder(new EmptyBorder(0, 50, 0, 0));

        // Crear el panelDerecho con los asientos
        panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setOpaque(false);

        //Panel con dni y botones de consultar y reservar
        topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        
        // Crear el JTextField para el DNI
        InputDni = new JTextField(8);
        OyenteTextField OyenteTextField = new OyenteTextField();
        InputDni.addActionListener(OyenteTextField);
        
        //Parte superior del panel topPanel (inputDni)
        topPanelSup = new JPanel();
        topPanelSup.setOpaque(false);

        //Parte inferior del panel topPanel (botones Consultar y Reservar)
        topPanelInf = new JPanel();
        topPanelInf.setOpaque(false);

        
        //TODO_Declarar y crear el OyenteBotonConsultar
        botonConsultar = new MiBoton("Consultar");
        //TODO_Registrar el oyente al botón
        botonConsultar.setEnabled(false); //inicialmente se encuentra deshabiliado
        OyenteBotonConsultar botonConsulta = new OyenteBotonConsultar();
        botonConsultar.addActionListener(botonConsulta);

        //TODO_Declarar y crear el OyenteBotonReserva
        botonReservar = new MiBoton("Reservar");
        //TODO_Registrar el oyente al botón
        botonReservar.setEnabled(false); //inicialmente se encuentra deshabiliado
        OyenteBotonReserva botonReserva = new OyenteBotonReserva();
        botonReservar.addActionListener(botonReserva);

        //Boton para eliminar la reserva consultada
        //TODO_Declarar y crear el OyenteBotonEliminarReserva
        botonEliminarReserva = new MiBoton("Eliminar Reserva");
        //TODO_Registrar el oyente al botón
        botonEliminarReserva.setVisible(false); //inicialmente se encuentra deshabiliado
        OyenteBotonEliminarReserva botonEliminaReserva = new OyenteBotonEliminarReserva();
        botonEliminarReserva.addActionListener(botonEliminaReserva);

        //Boton para "cerrar y limpiar" la reserva consultada
        //TODO_Declarar y crear el OyenteAceptar
        botonAceptar = new MiBoton("Aceptar");
        //TODO_Registrar el oyente al botón
        botonAceptar.setVisible(false); //inicialmente se encuentra deshabiliado
        OyenteAceptar oyenteBotonAceptar = new OyenteAceptar();
        botonAceptar.addActionListener(oyenteBotonAceptar);

        //Boton para realizar una nueva reserva
        //TODO_Declarar y crear el OyenteBotonCrearReserva
        botonAceptarReserva = new MiBoton("Reservar");
        //TODO_Registrar el oyente al botón
        botonAceptarReserva.setVisible(false); //inicialmente se encunetra deshabiliado
        OyenteBotonCrearReserva botonCreaReserva = new OyenteBotonCrearReserva();
        botonAceptarReserva.addActionListener(botonCreaReserva);
        
        //Boton "abortar" la operación de reserva
        //TODO_Declarar y crear el OyenteCancelarReserva
        botonCancelar = new MiBoton("Cancelar");
        //TODO_Registrar el oyente al botón
        botonCancelar.setVisible(false); //inicialmente se encunetra deshabiliado
        OyenteCancelarReserva botonCancelaReserva = new OyenteCancelarReserva();
        botonCancelar.addActionListener(botonCancelaReserva);

        // Agregar el JTextField al panel superior
        topPanelSup.add(new JLabel("DNI:"));
        topPanelSup.add(InputDni);
        // Agregar los botones al panel inferior
        topPanelInf.add(botonConsultar);
        topPanelInf.add(botonReservar);

        //agrego ambos paneles al panel superior
        topPanel.add(topPanelSup,BorderLayout.NORTH);
        topPanel.add(topPanelInf,BorderLayout.CENTER);
        topPanel.setBorder(new EmptyBorder(30, 0, 0, 0)); //Modifico margen superior

        //Crear panel con datos de la reserva
        datosPanel = new JPanel(new GridLayout(6,2));
        datosPanel.setVisible(false); //Inicialmente está no visible
        datosPanel.setOpaque(false);
        datosPanel.setBorder(new EmptyBorder(20, 30, 20, 20)); // Añade margenes en todos los lados
        crearEtiquetas(); //Creo todos los jlabel y jtextfield que van dentro del panel de datos

        //Agrego labels en panel datos reserva
        datosPanel.add(etiquetaNombre);
        datosPanel.add(etiquetaApellido);
        datosPanel.add(labelNombre);
        datosPanel.add(labelApellido);
        datosPanel.add(etiquetaDni);
        datosPanel.add(etiquetaCodTicket);
        datosPanel.add(labelDni);
        datosPanel.add(labelCodTicket);
        datosPanel.add(etiquetaUbicacion);
        JPanel aux = new JPanel();
        aux.setOpaque(false);        
        datosPanel.add(aux);
        datosPanel.add(labelUbicacion);
        
        //Panel para organizar los demás botones (EliminarReserva, Aceptar, Reservar y Cancelar)
        JPanel panelIzquierdoInferior = new JPanel(new FlowLayout());
        panelIzquierdoInferior.add(botonEliminarReserva);
        panelIzquierdoInferior.add(botonAceptar);
        panelIzquierdoInferior.add(botonAceptarReserva);
        panelIzquierdoInferior.add(botonCancelar);
        panelIzquierdoInferior.setOpaque(false);

        //Colocar los paneles en el panelIzquierdo
        panelIzquierdo.add(topPanel);
        panelIzquierdo.add(datosPanel);
        panelIzquierdo.add(panelIzquierdoInferior);

        // Panel superior (vacío-permite organizar mejor los componentes en pantalla)
        JPanel panelSuperior = new JPanel();
        panelSuperior.setPreferredSize(new Dimension(300, 200)); // Ajusta el tamaño según sea necesario
        panelSuperior.setOpaque(false);

        // Panel central (más alto) - Contiene el panel con los asientos
        JPanel panelCentral = new JPanel();
        panelCentral.setOpaque(false);

        //Panel de asientos
        panelButacas = new JPanel();
        panelButacas.setPreferredSize(new Dimension(120,300));
        panelButacas.setOpaque(false);
        panelButacas.setLayout(new GridLayout(15,5)); // se le establece un gridLayout con las medidas standar del tablero 
        inicializarBotones(); // inicializa cada uno de los botones de la matriz
        deshabilitarBotones(); //inicialmente los asientos estan deshabilitaods
        panelCentral.add(panelButacas); //agrego el panel de asiento al panel central que lo contiene

        panelDerecho.add(panelSuperior, BorderLayout.NORTH);
        panelDerecho.add(panelCentral, BorderLayout.CENTER);

        panelFondo.add(panelIzquierdo);
        panelFondo.add(panelDerecho);

        // Añadir los paneles al JLayeredPane
        layeredPane.add(panelImagenFondo, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(panelFondo, JLayeredPane.PALETTE_LAYER);
        this.add(layeredPane);
        this.setResizable(false);
    }

    //Método privado para crear las etiquetas iniciales, tipografías, colores, etc
    private void crearEtiquetas(){

        etiquetaDni = new JLabel("DNI");
        etiquetaDni.setFont(new Font("Arial", Font.BOLD, 14));
        etiquetaDni.setForeground(Color.black);

        etiquetaNombre = new JLabel("NOMBRE");
        etiquetaNombre.setFont(new Font("Arial", Font.BOLD, 14));
        etiquetaNombre.setForeground(Color.black);

        etiquetaApellido = new JLabel("APELLIDO");
        etiquetaApellido.setFont(new Font("Arial", Font.BOLD, 14));
        etiquetaApellido.setForeground(Color.black);

        etiquetaCodTicket = new JLabel("TICKET");
        etiquetaCodTicket.setFont(new Font("Arial", Font.BOLD, 14));
        etiquetaCodTicket.setForeground(Color.black);

        etiquetaUbicacion = new JLabel("UBICACION");
        etiquetaUbicacion.setFont(new Font("Arial", Font.BOLD, 14));
        etiquetaUbicacion.setForeground(Color.black);

        labelNombre = new JTextField();
        labelNombre.setFont(new Font("Arial", Font.PLAIN, 12));
        labelNombre.setForeground(Color.black);

        labelApellido = new JTextField();
        labelApellido.setFont(new Font("Arial", Font.PLAIN, 12));
        labelApellido.setForeground(Color.black);

        labelDni = new JTextField("");
        labelDni.setFont(new Font("Arial", Font.PLAIN, 12));
        labelDni.setForeground(Color.black);

        labelCodTicket = new JTextField();
        labelCodTicket.setFont(new Font("Arial", Font.PLAIN, 12));
        labelCodTicket.setForeground(Color.black);

        labelUbicacion = new JTextField();
        labelUbicacion.setFont(new Font("Arial", Font.PLAIN, 12));
        labelUbicacion.setForeground(Color.black);
    }

    //Método privado para deshabilitar todos los botones
    private void deshabilitarBotones(){      
        for(int i=0; i<botones.length;i++){
            for(int j=0; j<botones[0].length;j++){
                if(j!=botones[0].length/2){
                    botones[i][j].setEnabled(false);
                }
            }
        }       
    }

    //Método privado para actualizar los botones de acuerdo a las reservas
    private void habilitarBotones(){
        for(int i=0; i<botones.length;i++){
            for(int j=0; j<botones[0].length;j++){
                if(j!=botones[0].length/2){
                    if(logica.obtenerVuelo().consultarReserva(i, j)==null)
                        botones[i][j].setEnabled(true);
                    else
                        botones[i][j].setEnabled(false);
                }
            }
        }       
    }

    //Método privado para reiniciar ("limpiar") todos los campos ingresados por el usuario
    private void limpiarCamposDatos(){
        labelNombre.setText("");
        labelApellido.setText("");
        InputDni.setText("");
        labelCodTicket.setText("");
        labelUbicacion.setText("");
    }
    
    //TODO_implementar completo inicializarBotones
    //metodo que inicializa los botones que representan las butacas
    private void inicializarBotones(){

        // Crear la matriz de botones
        // Por cada boton de la matriz:
            //crear el boton
            //modificar el color a blanco para que se vean mejor los iconos
            //setear el action command "i-j"
            //establecer su oyente
            //habilitar el boton
            //setear el icono
            //Agregar el boton al panel de butacas (panelButacas)
        botones = new JButton[15][5];
        for(int i = 0; i < botones.length; i++)
            for(int j = 0; j < botones[0].length; j++) {
                botones[i][j] = new JButton();
                if(botones[0].length/2 == j)    {
                    botones[i][j].setBackground(Color.WHITE);
                    botones[i][j].setEnabled(false);
                }
                else {
                    botones[i][j].setBackground(Color.WHITE);
                    OyenteBotonCelda oyenteBotonCelda = new OyenteBotonCelda();
                    botones[i][j].addActionListener(oyenteBotonCelda);
                    botones[i][j].setEnabled(true);
                    botones[i][j].setIcon(new ImageIcon("asiento.png"));
                    botones[i][j].setActionCommand(i+"-"+j);
                }
                panelButacas.add(botones[i][j]);
            }


            //Tenga en cuenta que si el boton corresponde a la fila del pasillo (cant columnas/2),
            // se debe insertar un panel vacío (color blanco)
    }

    //TODO_completar la implementación de  OyenteBotonCelda
    //Oyente de botones de butacas
    private class OyenteBotonCelda implements ActionListener{

        public void actionPerformed(ActionEvent e){

            //TODO
            // Obtengo el identificador del botón seleccionado
            // Obtengo número de fila del botón (i)
            // Obtengo número de columna del botón (j)
            String aux = e.getActionCommand();
            String [] arr = aux.split("-");
            int i = Integer.parseInt(arr[0]);
            int j = Integer.parseInt(arr[1]);
            


            // Si hay un botón seleccionado previamente, restablece su icono a "no elegido"
            if (prevI != -1 && prevJ != -1) {
                botones[prevI][prevJ].setIcon(new ImageIcon("asiento.png"));
                botones[prevI][prevJ].setEnabled(true);
            }

            // Actualiza el icono del botón actualmente seleccionado
            botones[i][j].setIcon(new ImageIcon("asientoSeleccionado.png"));

            // Actualiza la ubicación seleccionada y el label
            String ubicacionButaca = i + "-" + j;
            labelUbicacion.setText(ubicacionButaca);

            /* Guarda el índice del botón seleccionado actualmente como el nuevo "anterior".
            * Esto es porque el usuario puede modificar su elección de asiento múltiples 
            * veces antes de confirmar/abortar su reserva
             */
            prevI = i;
            prevJ = j;

        }
    }

    //TODO_implementar completo el OyenteBotonCelda
    //Oyente boton Consultar reserva
    private class OyenteBotonConsultar implements ActionListener{
        public void actionPerformed(ActionEvent e){
            deshabilitarBotones();
            botonConsultar.setVisible(false);
            botonReservar.setVisible(false);
            String doc = labelDni.getText();
            int dni = Integer.parseInt("doc");
                if (logica.obtenerVuelo().hayReservaConDni(dni) != null) {
                    datosPanel.setVisible(true);
                    datosPanel.setEnabled(false);
                }
                else {
                    JOptionPane.showMessageDialog(null, "No se encontró una reserva con el DNI ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    InputDni.setText("");
                }

            //Deshabilito los botones de asientos para que el usuario no pueda seleccionarlos
            //Deshabilito los botones Consultar y Reservar
            // Leer el DNI ingresado
            //Consulto en la lógica si hay vuelos con el DNI ingresado            
            //si hay reserva con el dni ingresado, seteo los datos de la reserva en pantalla.
                // Actualizar el texto de los labels
                //Visibilizo el panel con datos de reservas
                //Deshabilito los campos para que el usuario no pueda modificarlos
                //Visibilizo los botones de Eliminar Reserva y Aceptar
            //sino
                //muestro mensaje al usuario
                //limpio el jTextField de dni
        }
    }

    //TODO_implementar completo el OyenteBotonReserva
    //Oyente boton Reservar
    private class OyenteBotonReserva implements ActionListener{
        public void actionPerformed(ActionEvent e){

            //Deshabilito los botones Consultar y Reservar
            botonConsultar.setEnabled(false);
            botonReservar.setEnabled(false);
            //Leer el DNI ingresado
            String aux = labelDni.getText();
            int dni = Integer.parseInt(aux);
            //Consulto en la lógica si hay vuelos con el DNI ingresado 
            if(logica.obtenerVuelo().hayReservaConDni(dni) == null) {         
            //si no hay reserva con el dni ingresado, seteo los datos de la reserva en pantalla.
                //Habilito los botones de asientos para que el usuario no pueda seleccionarlos
                deshabilitarBotones();
                habilitarBotones();
                //Habilito los campos para que el usuario pueda modificarlos
                datosPanel.setEnabled(true);
                //Visibilizo los botones de Eliminar Reserva y Aceptar
                botonCancelar.setVisible(true);
                botonCancelar.setEnabled(true);
                botonAceptarReserva.setVisible(true);
                botonAceptarReserva.setEnabled(true);
                //Visibilizo el panel con datos de reservas
                datosPanel.setVisible(true);
                //El codigo de ticket no debe ser ingresado por el usuario
                labelCodTicket.setEnabled(false);
                //La ubicación se modifica sólo al seleccionar en la grilla de botones
                labelUbicacion.setEnabled(false);
            }

                //Modifico el label del dni (el labelDni coincide con el dni ingresado)
                /*
                 * Reinicio las posiciones de el "último" asiento seleccionado por el usuario
                 * Esto es porque el usuario puede modificar su elección de butaca más de una vez
                 * antes de aceptar la reserva
                 */
                
            //sino
            else {
                InputDni.setText("");
                JOptionPane.showMessageDialog(null, "Ya existe una reserva con el DNI ingresado.", "DNI ya usado", JOptionPane.ERROR_MESSAGE);
            }
                //muestro mensaje al usuario
                //limpio el jTextField de dni
        }
    }

    //Oyente "enter" en campo de dniInput
    private class OyenteTextField implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //Habilita los botones de Consultar y Reservar
            botonConsultar.setEnabled(true);
            botonReservar.setEnabled(true);

            //Copio el contenido de dniInput en el label de DNI
            labelDni.setText(InputDni.getText());
        }
    }

    //TODO_implementar completo el OyenteBotonEliminarReserva
    //Oyente boton EliminarReserva
    private class OyenteBotonEliminarReserva implements ActionListener{
        public void actionPerformed(ActionEvent e){

            //Obtengo el dni de la reserva en pantalla
            //Obtengo la reserva eliminada
            //si elimine
                //Obtengo la posicion del boton para setear el icono del boton a partir de la ubicacion de la reserva
                //Muestro un mensaje informativo                
                //Limpio todos los campos de datos 
                //Oculto el panel de datos de la reserva y los botones (EliminarReserva y Aceptar)
            //sino
                //muestro un mensaje
        }
    }

    //Oyente boton Aceptar (opcion Consultar)
    private class OyenteAceptar implements ActionListener{
        public void actionPerformed(ActionEvent e){

            /*Luego de consultar por una reserva, el usuario puede Aceptar 
            y se debe reiniciar la pantalla */

            //Oculto el panel de datos de la reserva y los botones correspondientes (EliminarReserva y Aceptar)
            datosPanel.setVisible(false);
            botonAceptar.setVisible(false);
            botonEliminarReserva.setVisible(false);
            //Limpio los JTextfield
            limpiarCamposDatos();
        }
    }

    /*Oyente para el boton de Cancelar en la seccion de "reserva".
    Permite "abortar" una operación de reserva*/
    private class OyenteCancelarReserva implements ActionListener{
        public void actionPerformed(ActionEvent e){

            //Al "abortar" la operación de reserva, limpio todos los campos
            limpiarCamposDatos();

            //Modifico el icono del ultimo asiento seleccionado
            if(prevI>=0 && prevJ>=0) //Si el usuario llegó a seleccionar el asiento
                botones[prevI][prevJ].setIcon(new ImageIcon("asiento.png"));
            
            //Reinicio los atributos
            prevI = -1;
            prevJ = -1;

            deshabilitarBotones();

            //Reinicio la pantalla para nueva consulta/reserva
            datosPanel.setVisible(false);
            botonAceptar.setVisible(false);
            botonEliminarReserva.setVisible(false);
            botonCancelar.setVisible(false);
            botonAceptarReserva.setVisible(false);
        }
    }

    //TODO_implementar completo el OyenteBotonCrearReserva
    //Oyente boton Reservar
    private class OyenteBotonCrearReserva implements ActionListener{
        public void actionPerformed(ActionEvent e){

            //Primero obtengo todos los campos ingresados por el usuario
            String doc = labelDni.getText();
            int dni = Integer.parseInt(doc);
            String nombre = labelNombre.getText();
            String apellido = labelApellido.getText();
            String ubicacion = labelUbicacion.getText(); 
            String [] arr = ubicacion.split("-");
            int i = Integer.parseInt(arr[0]);
            int j = Integer.parseInt(arr[1]);          
            //Si el usuario ingreso todos los campos y seleccionó el asiento
            if(nombre != null && apellido != null && ubicacion != null) {
                //realizo la reserva
                labelCodTicket.setText(nombre.substring(0, 1)+apellido.substring(0, 1)+labelDni.getText());
                String ticket = labelCodTicket.getText();
                Reserva r = new Reserva(dni, nombre, apellido, ticket, ubicacion);
                logica.obtenerVuelo().reservar(i, j, r);
                //muestro un mensaje
                JOptionPane.showMessageDialog(null, "¡Creaste la reserva con éxito! Código del ticket: "+ticket, "Reserva exitosa", JOptionPane.INFORMATION_MESSAGE);
                //Reinicio a la pantalla principal
                if(prevI >= 0 && prevJ >= 0)
                    botones[prevI][prevJ].setIcon(new ImageIcon("asiento.png"));
                prevI = -1;
                prevJ = -1;
                datosPanel.setVisible(false);
                botonAceptar.setVisible(false);
                botonCancelar.setVisible(false);
                botonEliminarReserva.setVisible(false);
                botonAceptarReserva.setVisible(false);
                //limpio los campos de datos y deshabilito los botones de los asientos
                deshabilitarBotones();
                limpiarCamposDatos();
            }
            else{
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los datos de la reserva.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            //sino le muestro un mensaje de error
                //muestro un mensaje                     
        }
    }
}
