import javax.swing.*;

public class App {

    public static void main(String[] args) {

    	JDialog dialog = new JDialog();
    	
    	String[] values = {"COM1", "COM2"};
    	
    	Object result = JOptionPane.showInputDialog(dialog, "Choisir un port : ", "Connexion", JOptionPane.QUESTION_MESSAGE,
    	        null, values, "Titan");
    	
    	System.out.print(result);
    
    	
    	/*
    	
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                Model model = new Model();
                View view = new View();

                ControlGroup controlGroup = new ControlGroup(model, view);

                view.lock();
                view.askControl();
            }
        });
        
        */
    }
}
