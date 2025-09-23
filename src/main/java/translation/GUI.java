package translation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;


// TODO Task D: Update the GUI for the program to align with UI shown in the README example.
//            Currently, the program only uses the CanadaTranslator and the user has
//            to manually enter the language code they want to use for the translation.
//            See the examples package for some code snippets that may be useful when updating
//            the GUI.
public class GUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            Translator translator = new JSONTranslator();

            // Language Selector
            JPanel languagePanel = new JPanel();
            JComboBox<String> languageList = new JComboBox<>();
            for(String languageCode : translator.getLanguageCodes()) {
                languageList.addItem(languageCode);
            }
            languagePanel.add(new JLabel("Language:"));
            languagePanel.add(languageList);
            // JTextField languageField = new JTextField(10);
            // languagePanel.add(new JLabel("Language:"));
            // languagePanel.add(languageField);

            
            // Country Selector
            JPanel countryPanel = new JPanel();
            String[] countryCodes = new String[translator.getCountryCodes().size()];
            int i = 0;
            for(String countryCode : translator.getCountryCodes()) {
                countryCodes[i++] = countryCode;
            }
            JList<String> countryComboBox = new JList<>(countryCodes);
            JScrollPane scrollPane = new JScrollPane(countryComboBox);
            countryPanel.add(new JLabel("Country:"));
            countryPanel.add(scrollPane);
            // JTextField countryField = new JTextField(10);
            // countryField.setText("can");
            // countryField.setEditable(true);
            // countryPanel.add(new JLabel("Country:"));
            // countryPanel.add(countryField); 

            // Submit Button
            JPanel buttonPanel = new JPanel();
            JButton submit = new JButton("Submit");
            buttonPanel.add(submit);

            // Result
            JLabel resultLabelText = new JLabel("Translation:");
            buttonPanel.add(resultLabelText);
            JLabel resultLabel = new JLabel("\t\t\t\t\t\t\t");
            buttonPanel.add(resultLabel);


            // adding listener for when the user clicks the submit button
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String language = languageList.getSelectedItem().toString();
                    String country = countryComboBox.getSelectedValuesList().toArray(new String[0])[0];

                    // for now, just using our simple translator, but
                    // we'll need to use the real JSON version later.
                    // Translator translator = new JSONTranslator();

                    String result = translator.translate(country, language);
                    if (result == null) {
                        result = "no translation found!";
                    }
                    resultLabel.setText(result);

                }

            });

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(languagePanel);
            mainPanel.add(countryPanel);
            mainPanel.add(buttonPanel);

            JFrame frame = new JFrame("Country Name Translator");
            frame.setContentPane(mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setMinimumSize(new java.awt.Dimension(400, 300));
            frame.pack();
            frame.setVisible(true);


        });
    }
}
