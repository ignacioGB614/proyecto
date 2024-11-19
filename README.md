Código OyenteBotonCelda:

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
    
