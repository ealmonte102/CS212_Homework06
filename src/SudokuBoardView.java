/**
 * Created by Evan Almonte on 12/11/2015.
 */
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class SudokuBoardView extends JFrame {
    private SudokuBoard theBoard;
    private JTextField outputField;
    private JRadioButton checkIfValidRadio;
    private JRadioButton enterRadio;
    private JRadioButton clearRadio;
    private JTextField inputField;
    private JButton [][] sudokuButtons;

    public SudokuBoardView(SudokuBoard theBoard, int height, int width) {
        super("Welcome To Sudoku");
        this.theBoard = theBoard;
        UIManager.put("Button.disabledText", new ColorUIResource(Color.BLACK));
        initComponents();
        setPreferredSize(new Dimension(width, height));
        pack();
    }

    private void initComponents() {
        initOutputField();
        initRadioButtons();
        JPanel topContent = new JPanel(new BorderLayout());
        JPanel topContentOpts = new JPanel(new GridBagLayout());
        initTopPanel(topContent, topContentOpts);
        JPanel boardPanel = new JPanel(new GridLayout(theBoard.boardSize, theBoard.boardSize));
        initSudokuBtns(boardPanel);
        initInputField();
        add(boardPanel, BorderLayout.CENTER);
        add(topContent, BorderLayout.NORTH);
        add(inputField, BorderLayout.SOUTH);
    }

    private void initOutputField() {
        outputField = new JTextField(20);
        outputField.setEditable(false);
        outputField.setHorizontalAlignment((int)CENTER_ALIGNMENT);
    }
    private void initInputField() {
        inputField = new JTextField(20);
        inputField.setEditable(true);
        inputField.setHorizontalAlignment((int)CENTER_ALIGNMENT);
    }
    private void initRadioButtons() {
        ButtonGroup radioOptionGroup= new ButtonGroup();
        checkIfValidRadio = new JRadioButton("Validate Move", true);
        enterRadio = new JRadioButton("Enter Move");
        clearRadio = new JRadioButton("Clear Board");

        radioOptionGroup.add(checkIfValidRadio);
        radioOptionGroup.add(enterRadio);
        radioOptionGroup.add(clearRadio);
    }
    private void initTopPanel(JPanel topPanel, JPanel optionPanel) {
        topPanel.add(outputField, BorderLayout.NORTH);
        topPanel.add(optionPanel,BorderLayout.SOUTH);
        topPanel.setSize(getSize());
        optionPanel.add(checkIfValidRadio);
        optionPanel.add(enterRadio);
        optionPanel.add(clearRadio);
    }
    private void initSudokuBtns(JPanel boardPanel) {
        int numOfQuadrants = (int)Math.sqrt(theBoard.boardSize);
        sudokuButtons = new JButton[theBoard.boardSize][theBoard.boardSize];
        for(int i = 0; i < theBoard.boardSize; i++) {
            for(int j = 0; j < theBoard.boardSize; j++) {
                sudokuButtons[i][j] = new JButton();
                String boardValue = Integer.toString(theBoard.getSquare(i,j).getValue());
                //Set the value of each button, if the value is "0" leave the button blank.
                if(boardValue.equals("0")) {
                    boardValue = "";
                    sudokuButtons[i][j].setBackground(new Color(179,217,255));
                }
                else {
                    sudokuButtons[i][j].setBackground(new Color(255,149,110));
                    sudokuButtons[i][j].setEnabled(false);
                }
                //Creates the grid border
                if(i != 0 && j != 0 && j % numOfQuadrants == 0 && i % numOfQuadrants == 0) {
                    sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(3,3,1,1,Color.black));
                } else if (j % numOfQuadrants == 0 && j != 0) {
                    sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(1,3,1,1,Color.black));
                } else if(i % numOfQuadrants == 0 && i != 0) {
                    sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(3,1,1,1,Color.black));
                } else {
                    sudokuButtons[i][j].setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
                }
                sudokuButtons[i][j].setText(boardValue);
                //Set the action command to represent the row and column the button corresponds to.
                sudokuButtons[i][j].setActionCommand(i + "," + j);
                boardPanel.add(sudokuButtons[i][j]);
            }
        }
    }

    public void addClearRadioListener(ActionListener crl) {
        clearRadio.addActionListener(crl);
    }

    public void addButtonListener(ActionListener bl, int row, int col) {
        sudokuButtons[row][col].addActionListener(bl);
    }

    public void setButtonText(String text, int row, int col) {
        sudokuButtons[row][col].setText(text);
    }

    public String getInputText() {
        return inputField.getText();
    }

    public void setOutputText(String textOutput) {
        outputField.setText(textOutput);
    }

    public void resetRadioSelection() {
        checkIfValidRadio.setSelected(true);
    }
        
    public boolean isCheckIfValidSelected() {
        return checkIfValidRadio.isSelected();
    }

    public void setModel(SudokuBoard model) {
        theBoard = model;
    }

    public void resetButtons() {
        for(int i = 0; i < theBoard.boardSize; ++i) {
            for(int j = 0; j < theBoard.boardSize; ++j) {
                String boardValue = Integer.toString(theBoard.getSquare(i,j).getValue());
                if(boardValue.equals("0")) { boardValue = ""; }
                sudokuButtons[i][j].setText(boardValue);
            }
        }
    }
}
