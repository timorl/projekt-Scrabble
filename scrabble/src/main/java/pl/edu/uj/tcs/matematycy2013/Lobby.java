package pl.edu.uj.tcs.matematycy2013;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class Lobby extends javax.swing.JFrame {

    private Config config;
    private boolean defaultBoardSelected = true;
    private File boardFile;
    private boolean englishDict = false;
    private boolean isTorus = false;
    private File bagFile;
    private boolean defaultBagSelected = true;
    private int time = 0;

    /**
     * Creates new form L
     */
    public Lobby(Config config) {
        super("Scrabble");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("icon.jpe");
		try {
			Image im = ImageIO.read(input);
			setIconImage(im);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        this.setResizable(false);
        this.config = config;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        languageGroup = new javax.swing.ButtonGroup();
        topologyGroup = new javax.swing.ButtonGroup();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4), new java.awt.Dimension(32767, 4));
        polishDictionary = new javax.swing.JRadioButton();
        defaultBag = new javax.swing.JCheckBox();
        defaultBoard = new javax.swing.JCheckBox();
        standardTopology = new javax.swing.JRadioButton();
        torusTopology = new javax.swing.JRadioButton();
        maxTime = new javax.swing.JSpinner(new SpinnerNumberModel(5, 3, 100, 1));
        uploadBag = new javax.swing.JButton();
        uploadBoard = new javax.swing.JButton();
        englishDictionary = new javax.swing.JRadioButton();
        player1 = new javax.swing.JTextField();
        player2 = new javax.swing.JTextField();
        start = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Players:");

        jLabel2.setText("Dictionary: ");

        jLabel3.setText("Board type:");

        jLabel4.setText("Bag:");

        jLabel5.setText("Board topology:");

        jLabel6.setText("Time:");

        polishDictionary.setText("polish");
        polishDictionary.setToolTipText("");
        polishDictionary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                polishDictionaryActionPerformed(evt);
            }
        });

        defaultBag.setText("default");
        defaultBag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultBagActionPerformed(evt);
            }
        });

        defaultBoard.setText("default");
        defaultBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultBoardActionPerformed(evt);
            }
        });

        standardTopology.setText("standard");
        standardTopology.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                standardTopologyActionPerformed(evt);
            }
        });

        torusTopology.setText("torus");
        torusTopology.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                torusTopologyActionPerformed(evt);
            }
        });

        maxTime.setFocusable(false);
        maxTime.setPreferredSize(new java.awt.Dimension(50, 28));

        uploadBag.setText("Upload");
        uploadBag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadBagActionPerformed(evt);
            }
        });

        uploadBoard.setText("Upload");
        uploadBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadBoardActionPerformed(evt);
            }
        });

        englishDictionary.setText("english");
        englishDictionary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                englishDictionaryActionPerformed(evt);
            }
        });

        player1.addKeyListener(
                new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        player1Field1KeyTyped(evt);
                    }
                });

        player2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                player2Field1KeyTyped(evt);
            }
        });

        start.setText("START");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    startActionPerformed(evt);
                }
                catch (FileNotFoundException ex) {
                    Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IOException ex) {
                    Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        polishDictionary.setSelected(true);
        languageGroup.add(polishDictionary);
        languageGroup.add(englishDictionary);

        standardTopology.setSelected(true);
        topologyGroup.add(standardTopology);
        topologyGroup.add(torusTopology);

        defaultBoard.setSelected(true);
        defaultBag.setSelected(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel2)
                .addComponent(jLabel3)
                .addComponent(jLabel4)
                .addComponent(jLabel5)
                .addComponent(jLabel6)
                .addComponent(jLabel1))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(defaultBag)
                .addComponent(defaultBoard)
                .addComponent(polishDictionary)
                .addComponent(player1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(standardTopology))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(englishDictionary)
                .addComponent(uploadBoard)
                .addComponent(uploadBag)
                .addComponent(torusTopology)
                .addComponent(player2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(maxTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(start)))
                .addContainerGap(30, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(player1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(player2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(polishDictionary)
                .addComponent(englishDictionary))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(defaultBoard)
                .addComponent(uploadBoard))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(defaultBag)
                .addComponent(uploadBag))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(standardTopology)
                .addComponent(torusTopology))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(maxTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(start)
                .addContainerGap()));

        pack();
    }// </editor-fold>                        

    private void player1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void defaultBoardActionPerformed(java.awt.event.ActionEvent evt) {
       if(defaultBoardSelected){
            uploadBoardActionPerformed(evt);
        }
        else{
            defaultBoardSelected = !defaultBoardSelected;
        }
    }

    private void standardTopologyActionPerformed(java.awt.event.ActionEvent evt) {
        isTorus = false;
    }

    private void torusTopologyActionPerformed(ActionEvent evt) {
        isTorus = true;
    }

    private void polishDictionaryActionPerformed(java.awt.event.ActionEvent evt) {
        englishDict = false;
    }

    private void englishDictionaryActionPerformed(ActionEvent evt) {
        englishDict = true;
    }

    private void startActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException, IOException {
        this.setVisible(false);
        fillConfig(config);
        Scrabble.startGame(config);
    }

    private void player2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void uploadBoardActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        int v = fileChooser.showOpenDialog(null);

        if (v == JFileChooser.APPROVE_OPTION) {
            boardFile = fileChooser.getSelectedFile();
            defaultBoard.setSelected(false);
            defaultBoardSelected = false;
        }
        else {
            defaultBoard.setSelected(true);
            defaultBoardSelected = true;
        }
    }

    private void uploadBagActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        int v = fileChooser.showOpenDialog(null);

        if (v == JFileChooser.APPROVE_OPTION) {
            bagFile = fileChooser.getSelectedFile();
            defaultBag.setSelected(false);
            defaultBagSelected = false;
        }
        else {
            defaultBag.setSelected(true);
            defaultBagSelected = true;
        }
    }

    private void defaultBagActionPerformed(java.awt.event.ActionEvent evt) {
        if(defaultBagSelected){
            uploadBagActionPerformed(evt);
        }
        else{
            defaultBagSelected = !defaultBagSelected;
        }
    }

    private void player1Field1KeyTyped(java.awt.event.KeyEvent evt) {
        if (player1.getText().length() >= 10) {
            player1.setText(player1.getText().substring(0, 9));
        }
    }

    private void player2Field1KeyTyped(java.awt.event.KeyEvent evt) {
        if (player2.getText().length() >= 10) {
            player2.setText(player1.getText().substring(0, 9));
        }
    }
    // Variables declaration - do not modify       
    private javax.swing.ButtonGroup languageGroup;
    private javax.swing.ButtonGroup topologyGroup;
    private javax.swing.JCheckBox defaultBag;
    private javax.swing.JCheckBox defaultBoard;
    private javax.swing.JRadioButton englishDictionary;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton start;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSpinner maxTime;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField player1;
    private javax.swing.JTextField player2;
    private javax.swing.JRadioButton polishDictionary;
    private javax.swing.JRadioButton standardTopology;
    private javax.swing.JRadioButton torusTopology;
    private javax.swing.JButton uploadBag;
    private javax.swing.JButton uploadBoard;
    // End of variables declaration                   

    public void fillConfig(Config config) throws FileNotFoundException {
        String nick1 = player1.getText();
        if (nick1.length() == 0) {
            nick1 = "Player1";
        }

        String nick2 = player2.getText();
        if (nick2.length() == 0) {
            nick2 = "Player2";
        }

        config.setPlayerNames(nick1, nick2);
        config.setMaxTime(60 * (Integer) (maxTime.getValue()));

        if (!englishDict) {
            config.setDictionary("pl", true);
        }
        else {
            config.setDictionary("en", true);
        }

        if (defaultBoardSelected) {
            config.setDefaultBoard(isTorus);
        }
        else {
            config.setBoard(boardFile.getPath(), isTorus);
        }

        if (defaultBagSelected) {
            if (englishDict) {
                config.setBag("en", true);
            }
            else {
                config.setBag("pl", true);
            }
        }
        else {
            config.setBag(bagFile.getPath(), false);
        }
    }
}
