//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package puryfi;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPopupMenu.Separator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.Desktop.Action;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class NSWFAPI extends JFrame {
    public static final Logger LOGGER = LoggerFactory.getLogger(NSWFAPI.class.getName());

    final ImageLoader imageLoader;

    File[] input;
    public static File output_folder = new File("output");
    public static File temp = new File("output/temp");
    List<NSWF_Image> converter = new ArrayList<>();
    int displayed_index = 0;
    public boolean editmode = false;
    int mouse_m_x = 0;
    int mouse_m_y = 0;
    NSFW_BoundingBox pressed = null;
    public static NSFW_BoundingBox del_buf = null;
    Point pressed_event = null;
    int p_x;
    int p_y;
    NSFW_BoundingBox pop_selection = null;
    Point new_bb;
    int bb_x;
    int bb_y;
    static Process process;
    private JRadioButtonMenuItem ac_RadioButtonMenuItem;
    private JRadioButtonMenuItem ae_RadioButtonMenuItem;
    private JRadioButtonMenuItem anc_RadioButtonMenuItem;
    private JRadioButtonMenuItem ane_RadioButtonMenuItem;
    public static JCheckBox anus_c_button;
    public static JCheckBox anus_e_button;
    public static JCheckBox armpits_c_button;
    public static JCheckBox armpits_e_button;
    public static JRadioButton barButton;
    private JRadioButtonMenuItem belly_c_RadioButtonMenuItem;
    public static JCheckBox belly_c_button;
    private JRadioButtonMenuItem belly_e_RadioButtonMenuItem;
    public static JCheckBox belly_e_button;
    public static JRadioButton blurButton;
    private JRadioButtonMenuItem buttocks_c_RadioButtonMenuItem;
    public static JCheckBox buttocks_c_button;
    private JRadioButtonMenuItem buttocks_e_RadioButtonMenuItem;
    public static JCheckBox buttocks_e_button;
    private JLabel censoredImageLabel;
    private JButton editButton;
    private JRadioButtonMenuItem fbreast_c_RadioButtonMenuItem;
    private JRadioButtonMenuItem fbreast_e_RadioButtonMenuItem;
    public static JCheckBox fbreats_c_button;
    public static JCheckBox fbreats_e_button;
    private JRadioButtonMenuItem fc_RadioButtonMenuItem;
    private JRadioButtonMenuItem fe_RadioButtonMenuItem;
    public static JCheckBox feet_c_button;
    public static JCheckBox feet_e_button;
    public static JCheckBox ff_CheckBox;
    private JRadioButtonMenuItem ff_RadioButtonMenuItem;
    private JRadioButtonMenuItem fgen_c_RadioButtonMenuItem;
    public static JCheckBox fgen_c_button;
    private JRadioButtonMenuItem fgen_e_RadioButtonMenuItem;
    public static JCheckBox fgen_e_button;
    public static JCheckBox fm_CheckBox;
    private JRadioButtonMenuItem fm_RadioButtonMenuItem;
    private JCheckBox ignoreCheckBox;
    private JLabel jLabel4;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPopupMenu jPopupMenu1;
    public static JSpinner jSpinner1;
    public static JSpinner jSpinner2;
    private JToggleButton jToggleButton1;
    public static JComboBox<String> labelModeComboBox;
    private JRadioButtonMenuItem mbreast_c_RadioButtonMenuItem;
    public static JCheckBox mbreast_c_button;
    private JRadioButtonMenuItem mbreast_e_RadioButtonMenuItem;
    public static JCheckBox mbreast_e_button;
    private JRadioButtonMenuItem mgen_c_RadioButtonMenuItem;
    public static JCheckBox mgen_c_button;
    private JRadioButtonMenuItem mgen_e_RadioButtonMenuItem;
    public static JCheckBox mgen_e_button;
    private JRadioButton noAIRadioButton;
    private JLabel originalImageLabel;
    public static JRadioButton pixelButton;
    private JButton saveButton;
    private JLabel scoreLabel;
    private JButton startButton;

    public NSWFAPI(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;

        ActionListener actionListener = ae -> NSWFAPI.this.display(NSWFAPI.this.displayed_index);
        this.initComponents();
        this.editButton.setEnabled(output_folder.isDirectory() && output_folder.list().length > 0);
        ImageIcon img = new ImageIcon(this.getClass().getResource("/puryfi/puryfi.png"));
        this.setIconImage(img.getImage());
        mgen_e_button.addActionListener(actionListener);
        mgen_c_button.addActionListener(actionListener);
        buttocks_e_button.addActionListener(actionListener);
        buttocks_c_button.addActionListener(actionListener);
        belly_e_button.addActionListener(actionListener);
        belly_c_button.addActionListener(actionListener);
        fgen_e_button.addActionListener(actionListener);
        fgen_c_button.addActionListener(actionListener);
        fbreats_e_button.addActionListener(actionListener);
        fbreats_c_button.addActionListener(actionListener);
        mbreast_e_button.addActionListener(actionListener);
        mbreast_c_button.addActionListener(actionListener);
        fm_CheckBox.addActionListener(actionListener);
        ff_CheckBox.addActionListener(actionListener);
        feet_c_button.addActionListener(actionListener);
        feet_e_button.addActionListener(actionListener);
        armpits_c_button.addActionListener(actionListener);
        armpits_e_button.addActionListener(actionListener);
        anus_c_button.addActionListener(actionListener);
        anus_e_button.addActionListener(actionListener);
        if (!output_folder.exists()) {
            output_folder.mkdirs();
        }

        if (!temp.exists()) {
            if (!temp.mkdirs()) {

            }
        }

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher((KeyEventDispatcher) ke -> {
            Class var2 = NSWFAPI.class;
            synchronized (NSWFAPI.class) {
                switch (ke.getID()) {
                    case 401:
                        if (ke.getKeyCode() == 127 && NSWFAPI.del_buf != null) {
                            NSWF_Image get = (NSWF_Image) NSWFAPI.this.converter.get(NSWFAPI.this.displayed_index);
                            boolean remove = get.getResults().remove(NSWFAPI.del_buf);
                            NSWFAPI.this.display(NSWFAPI.this.displayed_index);
                            NSWFAPI.del_buf = null;
                            if (NSWFAPI.this.editmode) {
                                get.setEdited(true);
                            }
                        }
                    default:
                        return false;
                }
            }
        });
    }

    public static void openfolder(String dir) {
        File folder = new File(dir);
        if (folder.exists() && Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(folder);
            } catch (IOException var3) {
                LOGGER.error("Error Occured", var3);
            }
        }

    }


    public void display(int i) {
        if (!this.converter.isEmpty()) {
            this.displayed_index = i;
            this.jLabel9.setText("Preview: " + (this.displayed_index + 1) + " / " + this.converter.size());
            NSWF_Image get = (NSWF_Image) this.converter.get(i);
            this.scoreLabel.setText("<html>NSFW Score: <b>" + get.getNsfw_score());
            BufferedImage cens = get.getCensoredImage();
            BufferedImage org_rsize = get.getResizedPaintedImage(this.originalImageLabel);
            if (this.new_bb != null) {
                Rectangle viewportsize = this.originalImageLabel.getBounds();
                BufferedImage org = get.getBufferedImage();
                double scalex = (double) this.originalImageLabel.getWidth() / (double) org.getWidth();
                double scaley = (double) this.originalImageLabel.getHeight() / (double) org.getHeight();
                double scale = Math.min(scalex, scaley);
                int r_h = (int) ((double) org.getHeight() * scale);
                int r_w = (int) ((double) org.getWidth() * scale);
                int image_offset_y = (viewportsize.height - r_h) / 2;
                int image_offset_x = (viewportsize.width - r_w) / 2;
                int deltaX = this.mouse_m_x - this.bb_x;
                int deltaY = this.mouse_m_y - this.bb_y;
                Graphics2D g = (Graphics2D) org_rsize.getGraphics();
                g.setColor(Color.pink);
                if (deltaX > 0 && deltaY > 0) {
                    g.fillRect(this.bb_x - image_offset_x, this.bb_y - image_offset_y, deltaX, deltaY);
                } else if (deltaX < 0 && deltaY < 0) {
                    g.fillRect(this.bb_x - image_offset_x + deltaX, this.bb_y - image_offset_y + deltaY, -deltaX, -deltaY);
                } else if (deltaX < 0 && deltaY > 0) {
                    g.fillRect(this.bb_x - image_offset_x + deltaX, this.bb_y - image_offset_y, -deltaX, deltaY);
                } else if (deltaX > 0 && deltaY < 0) {
                    g.fillRect(this.bb_x - image_offset_x, this.bb_y - image_offset_y + deltaY, deltaX, -deltaY);
                }

                g.dispose();
            }

            BufferedImage cens_rsize = rsize(cens, this.censoredImageLabel);
            this.originalImageLabel.setIcon(new ImageIcon(org_rsize));
            this.censoredImageLabel.setIcon(new ImageIcon(cens_rsize));
            this.ignoreCheckBox.setSelected(((NSWF_Image) this.converter.get(this.displayed_index)).isIgnore());
        }

    }

    public static BufferedImage rsize(BufferedImage image, JLabel label) {
        double scalex = (double) label.getWidth() / (double) image.getWidth();
        double scaley = (double) label.getHeight() / (double) image.getHeight();
        double scale = Math.min(scalex, scaley);
        return Scalr.resize(image, Method.BALANCED, Mode.AUTOMATIC, (int) Math.max(1.0D, (double) image.getWidth() * scale), (int) Math.max(1.0D, (double) image.getHeight() * scale), new BufferedImageOp[]{Scalr.OP_ANTIALIAS});
    }

    private void initComponents() {
        ButtonGroup buttonGroup1 = new ButtonGroup();
        ButtonGroup buttonGroup2 = new ButtonGroup();
        this.jPopupMenu1 = new JPopupMenu();
        this.belly_c_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.belly_e_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.buttocks_c_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.buttocks_e_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.fbreast_c_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.fbreast_e_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.fgen_c_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.fgen_e_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.mbreast_c_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.mbreast_e_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.mgen_c_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.mgen_e_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.ff_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.fm_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.fc_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.fe_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.ac_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.ae_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.anc_RadioButtonMenuItem = new JRadioButtonMenuItem();
        this.ane_RadioButtonMenuItem = new JRadioButtonMenuItem();
        Separator jSeparator4 = new Separator();
        JMenuItem stickerMenuItem = new JMenuItem();
        Separator jSeparator2 = new Separator();
        JMenuItem deleteMenuItem = new JMenuItem();
        ButtonGroup buttonGroup3 = new ButtonGroup();
        ButtonGroup apiGroup = new ButtonGroup();
        JPanel jPanel1 = new JPanel();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JPanel comparePanel = new JPanel();
        this.originalImageLabel = new JLabel();
        this.censoredImageLabel = new JLabel();
        this.jLabel9 = new JLabel();
        JButton jButton4 = new JButton();
        JButton jButton6 = new JButton();
        buttocks_c_button = new JCheckBox();
        fbreats_c_button = new JCheckBox();
        buttocks_e_button = new JCheckBox();
        fbreats_e_button = new JCheckBox();
        JCheckBox jCheckBox5 = new JCheckBox();
        fgen_e_button = new JCheckBox();
        fgen_c_button = new JCheckBox();
        mgen_c_button = new JCheckBox();
        mgen_e_button = new JCheckBox();
        JLabel jLabel10 = new JLabel();
        this.saveButton = new JButton();
        barButton = new JRadioButton();
        pixelButton = new JRadioButton();
        blurButton = new JRadioButton();
        belly_c_button = new JCheckBox();
        belly_e_button = new JCheckBox();
        mbreast_c_button = new JCheckBox();
        mbreast_e_button = new JCheckBox();
        JLabel jLabel5 = new JLabel();
        this.jToggleButton1 = new JToggleButton();
        jSpinner1 = new JSpinner();
        this.ignoreCheckBox = new JCheckBox();
        this.scoreLabel = new JLabel();
        ff_CheckBox = new JCheckBox();
        fm_CheckBox = new JCheckBox();
        JPanel jPanel3 = new JPanel();
        JButton jButton2 = new JButton();
        this.jLabel8 = new JLabel();
        this.jLabel7 = new JLabel();
        this.startButton = new JButton();
        JLabel jLabel6 = new JLabel();
        JButton jButton1 = new JButton();
        JSeparator jSeparator1 = new JSeparator();
        JLabel jLabel11 = new JLabel();
        JRadioButton puryRadioButton = new JRadioButton();
        JSeparator jSeparator3 = new JSeparator();
        this.editButton = new JButton();
        this.noAIRadioButton = new JRadioButton();
        jSpinner2 = new JSpinner();
        this.jLabel4 = new JLabel();
        feet_c_button = new JCheckBox();
        feet_e_button = new JCheckBox();
        armpits_c_button = new JCheckBox();
        armpits_e_button = new JCheckBox();
        anus_c_button = new JCheckBox();
        anus_e_button = new JCheckBox();
        JLabel statusLabel = new JLabel();
        JLabel jLabel12 = new JLabel();
        labelModeComboBox = new JComboBox<>();
        JLabel jLabel2 = new JLabel();
        buttonGroup3.add(this.belly_c_RadioButtonMenuItem);
        this.belly_c_RadioButtonMenuItem.setText("Stomach / Belly - Covered");
        this.belly_c_RadioButtonMenuItem.addActionListener(NSWFAPI.this::belly_c_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.belly_c_RadioButtonMenuItem);
        buttonGroup3.add(this.belly_e_RadioButtonMenuItem);
        this.belly_e_RadioButtonMenuItem.setText("Stomach / Belly - Exposed");
        this.belly_e_RadioButtonMenuItem.addActionListener(NSWFAPI.this::belly_e_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.belly_e_RadioButtonMenuItem);
        buttonGroup3.add(this.buttocks_c_RadioButtonMenuItem);
        this.buttocks_c_RadioButtonMenuItem.setText("Buttocks - Covered");
        this.buttocks_c_RadioButtonMenuItem.addActionListener(NSWFAPI.this::buttocks_c_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.buttocks_c_RadioButtonMenuItem);
        buttonGroup3.add(this.buttocks_e_RadioButtonMenuItem);
        this.buttocks_e_RadioButtonMenuItem.setText("Buttocks - Exposed");
        this.buttocks_e_RadioButtonMenuItem.addActionListener(NSWFAPI.this::buttocks_e_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.buttocks_e_RadioButtonMenuItem);
        buttonGroup3.add(this.fbreast_c_RadioButtonMenuItem);
        this.fbreast_c_RadioButtonMenuItem.setText("Female Breast - Covered");
        this.fbreast_c_RadioButtonMenuItem.addActionListener(NSWFAPI.this::fbreast_c_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.fbreast_c_RadioButtonMenuItem);
        buttonGroup3.add(this.fbreast_e_RadioButtonMenuItem);
        this.fbreast_e_RadioButtonMenuItem.setText("Female Breast - Exposed");
        this.fbreast_e_RadioButtonMenuItem.addActionListener(NSWFAPI.this::fbreast_e_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.fbreast_e_RadioButtonMenuItem);
        buttonGroup3.add(this.fgen_c_RadioButtonMenuItem);
        this.fgen_c_RadioButtonMenuItem.setText("Female Genitalia - Covered");
        this.fgen_c_RadioButtonMenuItem.addActionListener(NSWFAPI.this::fgen_c_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.fgen_c_RadioButtonMenuItem);
        buttonGroup3.add(this.fgen_e_RadioButtonMenuItem);
        this.fgen_e_RadioButtonMenuItem.setText("Female Genitalia - Exposed");
        this.fgen_e_RadioButtonMenuItem.addActionListener(NSWFAPI.this::fgen_e_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.fgen_e_RadioButtonMenuItem);
        buttonGroup3.add(this.mbreast_c_RadioButtonMenuItem);
        this.mbreast_c_RadioButtonMenuItem.setText("Male Breast - Covered");
        this.mbreast_c_RadioButtonMenuItem.addActionListener(NSWFAPI.this::mbreast_c_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.mbreast_c_RadioButtonMenuItem);
        buttonGroup3.add(this.mbreast_e_RadioButtonMenuItem);
        this.mbreast_e_RadioButtonMenuItem.setText("Male Breast - Exposed");
        this.mbreast_e_RadioButtonMenuItem.addActionListener(NSWFAPI.this::mbreast_e_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.mbreast_e_RadioButtonMenuItem);
        buttonGroup3.add(this.mgen_c_RadioButtonMenuItem);
        this.mgen_c_RadioButtonMenuItem.setText("Male Genitalia - Covered");
        this.mgen_c_RadioButtonMenuItem.addActionListener(NSWFAPI.this::mgen_c_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.mgen_c_RadioButtonMenuItem);
        buttonGroup3.add(this.mgen_e_RadioButtonMenuItem);
        this.mgen_e_RadioButtonMenuItem.setText("Male Genitialia - Exposed");
        this.mgen_e_RadioButtonMenuItem.addActionListener(NSWFAPI.this::mgen_e_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.mgen_e_RadioButtonMenuItem);
        buttonGroup3.add(this.ff_RadioButtonMenuItem);
        this.ff_RadioButtonMenuItem.setText("Face - Female");
        this.ff_RadioButtonMenuItem.addActionListener(NSWFAPI.this::ff_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.ff_RadioButtonMenuItem);
        buttonGroup3.add(this.fm_RadioButtonMenuItem);
        this.fm_RadioButtonMenuItem.setText("Face - Male");
        this.fm_RadioButtonMenuItem.addActionListener(NSWFAPI.this::fm_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.fm_RadioButtonMenuItem);
        buttonGroup3.add(this.fc_RadioButtonMenuItem);
        this.fc_RadioButtonMenuItem.setText("Feet - Covered");
        this.fc_RadioButtonMenuItem.addActionListener(NSWFAPI.this::fc_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.fc_RadioButtonMenuItem);
        buttonGroup3.add(this.fe_RadioButtonMenuItem);
        this.fe_RadioButtonMenuItem.setText("Feet - Exposed");
        this.fe_RadioButtonMenuItem.addActionListener(NSWFAPI.this::fe_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.fe_RadioButtonMenuItem);
        buttonGroup3.add(this.ac_RadioButtonMenuItem);
        this.ac_RadioButtonMenuItem.setText("Armpits - Covered");
        this.ac_RadioButtonMenuItem.setToolTipText("");
        this.ac_RadioButtonMenuItem.addActionListener(NSWFAPI.this::ac_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.ac_RadioButtonMenuItem);
        buttonGroup3.add(this.ae_RadioButtonMenuItem);
        this.ae_RadioButtonMenuItem.setText("Armpits - Exposed");
        this.ae_RadioButtonMenuItem.addActionListener(NSWFAPI.this::ae_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.ae_RadioButtonMenuItem);
        buttonGroup3.add(this.anc_RadioButtonMenuItem);
        this.anc_RadioButtonMenuItem.setText("Anus - Covered");
        this.anc_RadioButtonMenuItem.addActionListener(NSWFAPI.this::anc_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.anc_RadioButtonMenuItem);
        buttonGroup3.add(this.ane_RadioButtonMenuItem);
        this.ane_RadioButtonMenuItem.setText("Anus - Exposed");
        this.ane_RadioButtonMenuItem.addActionListener(NSWFAPI.this::ane_RadioButtonMenuItemActionPerformed);
        this.jPopupMenu1.add(this.ane_RadioButtonMenuItem);
        this.jPopupMenu1.add(jSeparator4);
        stickerMenuItem.setText("Add / Remove Sticker");
        stickerMenuItem.addActionListener(NSWFAPI.this::stickerMenuItemActionPerformed);
        this.jPopupMenu1.add(stickerMenuItem);
        this.jPopupMenu1.add(jSeparator2);
        deleteMenuItem.setText("Delete");
        deleteMenuItem.addActionListener(NSWFAPI.this::deleteMenuItemActionPerformed);
        this.jPopupMenu1.add(deleteMenuItem);
        this.setDefaultCloseOperation(3);
        this.setTitle("Pury.fi");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                NSWFAPI.this.formWindowClosing(evt);
            }
        });
        jLabel1.setText("<html><a href=\"pury.fi\"/>www.pury.fi</a>");
        jLabel1.setCursor(new Cursor(12));
        jLabel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                NSWFAPI.this.jLabel1MouseClicked(evt);
            }
        });
        jLabel3.setFont(new Font("Tahoma", 1, 11));
        jLabel3.setText("Pury.fi Online Annotator v.3.7");
        comparePanel.setBorder(BorderFactory.createEtchedBorder());
        comparePanel.setLayout(new GridLayout(1, 0));
        this.originalImageLabel.setHorizontalAlignment(0);
        this.originalImageLabel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                NSWFAPI.this.originialimagelabelMouseDragged(evt);
            }

            public void mouseMoved(MouseEvent evt) {
                NSWFAPI.this.originialimagelabelMouseMoved(evt);
            }
        });
        this.originalImageLabel.addMouseWheelListener(NSWFAPI.this::originialimagelabelMouseWheelMoved);
        this.originalImageLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                NSWFAPI.this.originialimagelabelMouseClicked(evt);
            }

            public void mousePressed(MouseEvent evt) {
                NSWFAPI.this.originialimagelabelMousePressed(evt);
            }

            public void mouseReleased(MouseEvent evt) {
                NSWFAPI.this.originialimagelabelMouseReleased(evt);
            }
        });
        comparePanel.add(this.originalImageLabel);
        this.censoredImageLabel.setHorizontalAlignment(0);
        comparePanel.add(this.censoredImageLabel);
        this.jLabel9.setFont(new Font("Tahoma", 1, 11));
        this.jLabel9.setText("Preview:");
        jButton4.setFont(new Font("Tahoma", 1, 11));
        jButton4.setText("<html> <p>&rarr;</p> ");
        jButton4.setActionCommand("<html> <p>U+2B05</p> ");
        jButton4.addActionListener(NSWFAPI.this::jButton4ActionPerformed);
        jButton6.setFont(new Font("Tahoma", 1, 11));
        jButton6.setText("<html> <p>&larr;</p> ");
        jButton6.addActionListener(NSWFAPI.this::jButton6ActionPerformed);
        buttocks_c_button.setSelected(true);
        buttocks_c_button.setText("Buttocks - Covered");
        fbreats_c_button.setSelected(true);
        fbreats_c_button.setText("Female Breast - Covered");
        buttocks_e_button.setSelected(true);
        buttocks_e_button.setText("Buttocks - Exposed");
        fbreats_e_button.setSelected(true);
        fbreats_e_button.setText("Female Breast - Exposed");
        jCheckBox5.setSelected(true);
        jCheckBox5.setText("<html>Save <b>Json-Metadata");
        fgen_e_button.setSelected(true);
        fgen_e_button.setText("Female Genitalia - Exposed");
        fgen_c_button.setSelected(true);
        fgen_c_button.setText("Female Genitalia - Covered");
        mgen_c_button.setSelected(true);
        mgen_c_button.setText("Male Genitalia - Covered");
        mgen_e_button.setSelected(true);
        mgen_e_button.setText("Male Genitalia - Exposed");
        jLabel10.setText("Censor options:");
        this.saveButton.setFont(new Font("Tahoma", 1, 11));
        this.saveButton.setText("Save images");
        this.saveButton.setEnabled(false);
        this.saveButton.addActionListener(NSWFAPI.this::saveButtonActionPerformed);
        buttonGroup2.add(barButton);
        barButton.setSelected(true);
        barButton.setText("Black bar");
        barButton.addActionListener(NSWFAPI.this::barButtonActionPerformed);
        buttonGroup2.add(pixelButton);
        pixelButton.setText("Pixelation");
        pixelButton.addActionListener(NSWFAPI.this::pixelButtonActionPerformed);
        buttonGroup2.add(blurButton);
        blurButton.setText("Blur");
        blurButton.addActionListener(NSWFAPI.this::blurButtonActionPerformed);
        belly_c_button.setSelected(true);
        belly_c_button.setText("Stomach / Belly - Covered");
        belly_e_button.setSelected(true);
        belly_e_button.setText("Stomach / Belly - Exposed");
        mbreast_c_button.setSelected(true);
        mbreast_c_button.setText("Male Breast - Covered");
        mbreast_e_button.setSelected(true);
        mbreast_e_button.setText("Male Breast - Exposed");
        jLabel5.setText("Censor type:");
        this.jToggleButton1.setText("Toogle options");
        this.jToggleButton1.setFocusable(false);
        this.jToggleButton1.addActionListener(NSWFAPI.this::jToggleButton1ActionPerformed);
        jSpinner1.setModel(new SpinnerNumberModel(20, 1, (Comparable) null, 1));
        jSpinner1.setEnabled(false);
        jSpinner1.addChangeListener(NSWFAPI.this::jSpinner1StateChanged);
        this.ignoreCheckBox.setText("Ignore image");
        this.ignoreCheckBox.addActionListener(NSWFAPI.this::ignoreCheckBoxActionPerformed);
        this.scoreLabel.setVisible(false);
        this.scoreLabel.setText("<html>NSFW Score:");
        ff_CheckBox.setText("Face - Female");
        fm_CheckBox.setText("Face - Male");
        jButton2.setText("Select");
        jButton2.addActionListener(NSWFAPI.this::jButton2ActionPerformed);
        this.jLabel8.setText("Output folder: /output");
        this.jLabel7.setText("Input folder:");
        this.startButton.setFont(new Font("Tahoma", 1, 11));
        this.startButton.setText("Start");
        this.startButton.addActionListener(NSWFAPI.this::startButtonActionPerformed);
        jLabel6.setText("*max file size 4MB | max files per batch 50");
        jButton1.setText("Select");
        jButton1.addActionListener(NSWFAPI.this::jButton1ActionPerformed);
        jLabel11.setFont(new Font("Tahoma", 1, 11));
        jLabel11.setText("API:");
        apiGroup.add(puryRadioButton);
        puryRadioButton.setSelected(true);
        puryRadioButton.setText("pury.fi/detect");
        this.editButton.setFont(new Font("Tahoma", 1, 10));
        this.editButton.setText("Edit stored data");
        this.editButton.setFocusPainted(false);
        this.editButton.setFocusable(false);
        this.editButton.addActionListener(NSWFAPI.this::editButtonActionPerformed);
        apiGroup.add(this.noAIRadioButton);
        this.noAIRadioButton.setText("No AI");
        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel3Layout.createSequentialGroup().addComponent(jButton1).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jLabel7, -1, -1, 32767).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.startButton)).addComponent(jSeparator1).addComponent(jSeparator3).addComponent(this.editButton, Alignment.TRAILING, -1, -1, 32767).addGroup(Alignment.TRAILING, jPanel3Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(jLabel6)).addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(jButton2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jLabel8)).addGroup(jPanel3Layout.createSequentialGroup().addComponent(jLabel11, -2, 31, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(puryRadioButton).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.noAIRadioButton))).addGap(0, 0, 32767))).addContainerGap()));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.editButton, -2, 13, -2).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addComponent(this.startButton).addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE).addComponent(jButton1).addComponent(this.jLabel7))).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE).addComponent(jButton2).addComponent(this.jLabel8).addComponent(jLabel6)).addPreferredGap(ComponentPlacement.RELATED).addComponent(jSeparator1, -2, 4, -2).addGap(0, 0, 0).addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel11, -2, 23, -2).addComponent(puryRadioButton).addComponent(this.noAIRadioButton)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(jSeparator3, -2, -1, -2).addGap(36, 36, 36)));
        jSpinner2.setModel(new SpinnerNumberModel(0.75F, 0.05F, 0.95F, 0.05F));
        jSpinner2.setEnabled(false);
        jSpinner2.addChangeListener(NSWFAPI.this::jSpinner2StateChanged);
        this.jLabel4.setHorizontalAlignment(0);
        this.jLabel4.setText(" ");
        feet_c_button.setSelected(true);
        feet_c_button.setText("Feet - Covered");
        feet_e_button.setSelected(true);
        feet_e_button.setText("Feet - Exposed");
        armpits_c_button.setSelected(true);
        armpits_c_button.setText("Armpits - Covered");
        armpits_e_button.setSelected(true);
        armpits_e_button.setText("Armpits - Exposed");
        anus_c_button.setSelected(true);
        anus_c_button.setText("Anus - Covered");
        anus_e_button.setSelected(true);
        anus_e_button.setText("Anus - Exposed");
        statusLabel.setFont(new Font("Tahoma", 1, 11));
        statusLabel.setHorizontalAlignment(0);
        statusLabel.setText("By 0131 with special thanks to allusion, Skier23 and Valuta");
        statusLabel.setHorizontalTextPosition(10);
        jLabel12.setIcon(new ImageIcon(this.getClass().getResource("/puryfi/rsz_become_a_patron_button.png")));
        jLabel12.setCursor(new Cursor(12));
        jLabel12.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                NSWFAPI.this.jLabel12MouseClicked(evt);
            }
        });
        labelModeComboBox.setModel(new DefaultComboBoxModel(new String[]{"Full names", "Short names", "Hidden"}));
        labelModeComboBox.addActionListener(NSWFAPI.this::labelmodeComboBoxActionPerformed);
        jLabel2.setText("Label mode:");
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel1, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(statusLabel, -1, -1, 32767).addPreferredGap(ComponentPlacement.RELATED).addComponent(jLabel12)).addComponent(comparePanel, -1, -1, 32767).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel3).addGap(283, 283, 283)).addComponent(jPanel3, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED)).addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(ff_CheckBox, -1, -1, 32767).addComponent(fm_CheckBox, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(armpits_c_button, -1, -1, 32767).addComponent(feet_c_button, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(feet_e_button, -1, -1, 32767).addComponent(armpits_e_button, -1, -1, 32767)).addGap(11, 11, 11))).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel10).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jToggleButton1, -2, 220, -2)).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false).addComponent(anus_c_button, -1, -1, 32767).addComponent(fgen_c_button, Alignment.LEADING, -1, -1, 32767).addComponent(fbreats_c_button, Alignment.LEADING, -1, -1, 32767).addComponent(buttocks_c_button, Alignment.LEADING, -1, -1, 32767).addComponent(belly_c_button, Alignment.LEADING, -1, -1, 32767).addComponent(mgen_c_button, Alignment.LEADING, -1, -1, 32767).addComponent(mbreast_c_button, Alignment.LEADING, -1, -1, 32767)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(fbreats_e_button, Alignment.TRAILING, -1, -1, 32767).addComponent(mbreast_e_button, Alignment.TRAILING, -1, -1, 32767).addComponent(fgen_e_button, Alignment.TRAILING, -1, -1, 32767).addComponent(buttocks_e_button, -1, -1, 32767).addComponent(belly_e_button, -1, -1, 32767).addComponent(mgen_e_button, -1, -1, 32767).addComponent(anus_e_button, -1, -1, 32767)))).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(8, 8, 8).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel5).addGap(0, 0, 32767)).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(pixelButton, -1, -1, 32767).addComponent(barButton).addComponent(blurButton, -1, -1, 32767)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel4, -1, -1, 32767).addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGap(0, 0, 32767).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(jSpinner2, Alignment.TRAILING).addComponent(jSpinner1, Alignment.TRAILING, -1, 65, 32767))))))).addGroup(jPanel1Layout.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(labelModeComboBox, Alignment.TRAILING, 0, -1, 32767).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel2).addGap(0, 0, 32767)))))).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel9, -2, 285, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(jButton6, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(jButton4, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.ignoreCheckBox).addGap(8, 8, 8).addComponent(jCheckBox5, -2, -1, -2)).addComponent(this.scoreLabel, -2, 102, -2)).addGap(0, 0, 32767)).addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.saveButton, -2, 109, -2))).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jToggleButton1, Alignment.TRAILING, -2, 0, 32767).addComponent(jLabel10)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(belly_c_button).addComponent(belly_e_button)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(buttocks_e_button).addPreferredGap(ComponentPlacement.RELATED).addComponent(fbreats_e_button).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(fgen_e_button).addGroup(jPanel1Layout.createSequentialGroup().addGap(46, 46, 46).addComponent(mgen_e_button)))).addGroup(jPanel1Layout.createSequentialGroup().addComponent(buttocks_c_button).addPreferredGap(ComponentPlacement.RELATED).addComponent(fbreats_c_button).addPreferredGap(ComponentPlacement.RELATED).addComponent(fgen_c_button).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(mbreast_c_button).addComponent(mbreast_e_button)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(mgen_c_button).addComponent(feet_e_button).addComponent(feet_c_button).addComponent(fm_CheckBox))))).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel3, -2, 15, -2).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(jPanel3, -2, 110, -2).addGap(23, 23, 23))).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(armpits_e_button).addComponent(anus_c_button).addComponent(anus_e_button)).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(ff_CheckBox).addComponent(armpits_c_button)))).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel5).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(barButton).addComponent(this.jLabel4, -2, 23, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(pixelButton).addComponent(jSpinner1, -2, -1, -2)).addGap(1, 1, 1).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(blurButton).addComponent(jSpinner2, -2, -1, -2)).addPreferredGap(ComponentPlacement.RELATED).addComponent(jLabel2).addGap(9, 9, 9).addComponent(labelModeComboBox, -2, -1, -2))).addGap(2, 2, 2).addComponent(this.scoreLabel).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(jCheckBox5, -2, -1, -2).addComponent(this.saveButton).addComponent(this.ignoreCheckBox).addComponent(jButton4, -2, -1, -2).addComponent(jButton6, -2, -1, -2)).addGroup(jPanel1Layout.createSequentialGroup().addGap(4, 4, 4).addComponent(this.jLabel9, -2, 20, -2))).addPreferredGap(ComponentPlacement.UNRELATED, -1, 32767).addComponent(comparePanel, -1, 378, 32767).addGap(0, 0, 0).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(jLabel1, -2, 24, -2).addComponent(statusLabel, -1, -1, 32767).addComponent(jLabel12, -2, 23, -2))));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jPanel1, -1, -1, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jPanel1, -1, -1, 32767));
        this.pack();
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        Frame parent = new Frame();
        FileDialog fc = new FileDialog(parent, "Select Images", 0);
        fc.setDirectory("");
        fc.setMultipleMode(true);
        fc.setFile("*.jpg;*.jpeg;*.png");
        fc.setVisible(true);
        File[] files = fc.getFiles();
        if (files.length > 0) {
            this.input = files;
            if (this.input.length == 1 && this.input[0].isDirectory()) {
                File dir = this.input[0];
                this.input = dir.listFiles((dir1, name) -> (double) dir1.length() < 4.0D * Math.pow(10.0D, 6.0D) && (name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg")));
            }

            List<File> filter = new ArrayList();

            for (int i = 0; i < this.input.length && i < 50; ++i) {
                if ((double) this.input[i].length() < 4.0D * Math.pow(10.0D, 6.0D)) {
                    filter.add(this.input[i]);
                }
            }

            this.input = (File[]) filter.toArray(new File[0]);
            this.jLabel7.setText("Input folder: " + this.input.length + " files selected.");
        }

    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(1);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == 0) {
            output_folder = fc.getSelectedFile();
            if (returnVal == 0) {
                this.jLabel8.setText("Output folder: ..." + fc.getSelectedFile().getPath().substring(fc.getSelectedFile().getPath().length() - 20, fc.getSelectedFile().getPath().length() - 1));
            }
        }

    }

    private void startButtonActionPerformed(ActionEvent evt) {
        this.saveButton.setText("Save images");
        if (this.input != null) {
            Thread t = new Thread(new Runnable() {
                long timstamp = 0L;

                public void run() {
                    NSWFAPI.this.startButton.setEnabled(false);

                    for (int i = 0; i < NSWFAPI.this.input.length; ++i) {
                        File name = NSWFAPI.this.input[i];
                        this.timstamp = System.currentTimeMillis();
                        boolean noAi = NSWFAPI.this.noAIRadioButton.isSelected();
                        NSWF_Image convert = NSWFAPI.this.imageLoader.convert(name, noAi);
                        if (convert != null) {
                            NSWFAPI.this.converter.add(convert);
                            if (NSWFAPI.this.converter.size() == 1) {
                                NSWFAPI.this.display(NSWFAPI.this.converter.size() - 1);
                                NSWFAPI.this.saveButton.setText("Save images");
                                NSWFAPI.this.saveButton.setEnabled(true);
                            }
                        }

                        NSWFAPI.this.jLabel9.setText("Preview: " + (NSWFAPI.this.displayed_index + 1) + " / " + NSWFAPI.this.converter.size());
                    }

                    if (!NSWFAPI.this.converter.isEmpty()) {
                        NSWFAPI.this.saveButton.setEnabled(true);
                    }

                    NSWFAPI.this.startButton.setEnabled(true);
                }
            });
            t.start();
        }

    }

    private void jButton6ActionPerformed(ActionEvent evt) {
        if (this.displayed_index > 0) {
            this.display(this.displayed_index - 1);
        }

    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        if (this.displayed_index < this.converter.size() - 1) {
            this.display(this.displayed_index + 1);
        }

    }

    private void saveButtonActionPerformed(ActionEvent evt) {
        if (!this.converter.isEmpty()) {
            for (int i = 0; i < this.converter.size(); ++i) {
                if (this.editmode && !((NSWF_Image) this.converter.get(i)).isEdited()) {
                    ((NSWF_Image) this.converter.get(i)).saveCensoredImage();
                } else if (!((NSWF_Image) this.converter.get(i)).isIgnore()) {
                    ((NSWF_Image) this.converter.get(i)).saveImage();
                    if (this.editmode) {
                        ((NSWF_Image) this.converter.get(i)).getEditedsourcefileimage().delete();
                        ((NSWF_Image) this.converter.get(i)).getEditedsourcefiletxt().delete();
                    }
                }
            }

            this.editButton.setEnabled(output_folder.isDirectory() && output_folder.list().length > 0);
            openfolder("output");
        }

    }

    public static String[] readFile(String filepath) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "UTF8"));
            LineNumberReader lnr = new LineNumberReader(new FileReader(filepath));
            lnr.skip(9223372036854775807L);
            lnr.close();
            String[] filecontent = new String[lnr.getLineNumber() + 1];

            for (int i = 0; i < filecontent.length; ++i) {
                filecontent[i] = br.readLine();
            }

            String[] var16 = filecontent;
            return var16;
        } catch (IOException var14) {
            LOGGER.error("Error Occured", var14);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (NullPointerException | IOException var13) {
                LOGGER.error("Error Occured", var13);
            }

        }

        return null;
    }

    public static File[] getFiles(String foldername, final String[] filter) {
        return (new File(foldername)).listFiles((folder, name) -> {
            if (filter != null && filter.length > 0) {
                for (int i = 0; i < filter.length; ++i) {
                    if (name.endsWith(filter[i]) || name.endsWith(filter[i].toUpperCase())) {
                        return true;
                    }
                }

                return false;
            } else {
                return true;
            }
        });
    }

    private void originialimagelabelMouseClicked(MouseEvent evt) {
        if (this.pressed == null) {
            if (evt.isPopupTrigger() && this.doPopup(this.originalImageLabel, evt)) {
                return;
            }

            if (!this.converter.isEmpty() && evt.getClickCount() == 2) {
                NSWF_Image get = (NSWF_Image) this.converter.get(this.displayed_index);
                List<NSFW_BoundingBox> results = get.getResults();
                int x = evt.getX();
                int y = evt.getY();
                Rectangle viewportsize = this.originalImageLabel.getBounds();
                BufferedImage org = get.getBufferedImage();
                double scalex = (double) this.originalImageLabel.getWidth() / (double) org.getWidth();
                double scaley = (double) this.originalImageLabel.getHeight() / (double) org.getHeight();
                double scale = Math.min(scalex, scaley);
                int r_h = (int) ((double) org.getHeight() * scale);
                int r_w = (int) ((double) org.getWidth() * scale);
                int image_offset_y = (viewportsize.height - r_h) / 2;
                int image_offset_x = (viewportsize.width - r_w) / 2;

                for (int i = 0; i < results.size(); ++i) {
                    NSFW_BoundingBox get1 = (NSFW_BoundingBox) results.get(i);
                    Rectangle bounding_box = get1.bounding_box;
                    int bx = (int) (scale * (double) bounding_box.x) + image_offset_x;
                    int by = (int) (scale * (double) bounding_box.y) + image_offset_y;
                    int bw = (int) (scale * (double) (bounding_box.width + bounding_box.x)) + image_offset_x;
                    int bh = (int) (scale * (double) (bounding_box.height + bounding_box.y)) + image_offset_y;
                    if (bx <= x && bw >= x && by <= y && bh >= y) {
                        get1.setCensored(!get1.isCensored());
                        this.display(this.displayed_index);
                    }
                }
            }

            this.new_bb = null;
        }

    }

    private void jToggleButton1ActionPerformed(ActionEvent evt) {
        mgen_e_button.setSelected(!this.jToggleButton1.isSelected());
        mgen_c_button.setSelected(!this.jToggleButton1.isSelected());
        buttocks_e_button.setSelected(!this.jToggleButton1.isSelected());
        buttocks_c_button.setSelected(!this.jToggleButton1.isSelected());
        belly_e_button.setSelected(!this.jToggleButton1.isSelected());
        belly_c_button.setSelected(!this.jToggleButton1.isSelected());
        fgen_e_button.setSelected(!this.jToggleButton1.isSelected());
        fgen_c_button.setSelected(!this.jToggleButton1.isSelected());
        fbreats_e_button.setSelected(!this.jToggleButton1.isSelected());
        fbreats_c_button.setSelected(!this.jToggleButton1.isSelected());
        mbreast_e_button.setSelected(!this.jToggleButton1.isSelected());
        mbreast_c_button.setSelected(!this.jToggleButton1.isSelected());
        feet_c_button.setSelected(!this.jToggleButton1.isSelected());
        feet_e_button.setSelected(!this.jToggleButton1.isSelected());
        armpits_c_button.setSelected(!this.jToggleButton1.isSelected());
        armpits_e_button.setSelected(!this.jToggleButton1.isSelected());
        anus_e_button.setSelected(!this.jToggleButton1.isSelected());
        anus_c_button.setSelected(!this.jToggleButton1.isSelected());
        this.display(this.displayed_index);
    }

    private void pixelButtonActionPerformed(ActionEvent evt) {
        this.display(this.displayed_index);
        this.jLabel4.setText("Pixelsize");
        jSpinner1.setEnabled(true);
        jSpinner2.setEnabled(false);
    }

    private void barButtonActionPerformed(ActionEvent evt) {
        this.display(this.displayed_index);
        this.jLabel4.setText("");
        jSpinner1.setEnabled(false);
        jSpinner2.setEnabled(false);
    }

    private void blurButtonActionPerformed(ActionEvent evt) {
        this.display(this.displayed_index);
        this.jLabel4.setText("Blur | Edges");
        jSpinner1.setEnabled(true);
        jSpinner2.setEnabled(true);
    }

    private void jSpinner1StateChanged(ChangeEvent evt) {
        this.display(this.displayed_index);
    }

    private void ignoreCheckBoxActionPerformed(ActionEvent evt) {
        if (!this.converter.isEmpty()) {
            NSWF_Image get = (NSWF_Image) this.converter.get(this.displayed_index);
            get.setIgnore(this.ignoreCheckBox.isSelected());
        }

    }

    private void originialimagelabelMouseMoved(MouseEvent evt) {
        if (!this.converter.isEmpty()) {
            NSWF_Image get = (NSWF_Image) this.converter.get(this.displayed_index);
            List<NSFW_BoundingBox> results = get.getResults();
            int x = evt.getX();
            int y = evt.getY();

            Rectangle viewportsize = this.originalImageLabel.getBounds();
            BufferedImage org = get.getBufferedImage();
            double scalex = (double) this.originalImageLabel.getWidth() / (double) org.getWidth();
            double scaley = (double) this.originalImageLabel.getHeight() / (double) org.getHeight();
            double scale = Math.min(scalex, scaley);
            int r_h = (int) ((double) org.getHeight() * scale);
            int r_w = (int) ((double) org.getWidth() * scale);
            int image_offset_y = (viewportsize.height - r_h) / 2;
            int image_offset_x = (viewportsize.width - r_w) / 2;
            boolean hit = false;

            for (int i = 0; i < results.size(); ++i) {
                NSFW_BoundingBox get1 = (NSFW_BoundingBox) results.get(i);
                Rectangle bounding_box = get1.bounding_box;
                int bx = (int) (scale * (double) bounding_box.x) + image_offset_x;
                int by = (int) (scale * (double) bounding_box.y) + image_offset_y;
                int bw = (int) (scale * (double) (bounding_box.width + bounding_box.x)) + image_offset_x;
                int bh = (int) (scale * (double) (bounding_box.height + bounding_box.y)) + image_offset_y;
                if (bx <= x && bw >= x && by <= y && bh >= y) {
                    hit = true;
                    this.originalImageLabel.setCursor(Cursor.getPredefinedCursor(13));
                    break;
                }
            }

            if (!hit) {
                this.originalImageLabel.setCursor(Cursor.getPredefinedCursor(0));
            }
        }

    }

    public boolean doPopup(Component component, MouseEvent evt) {
        if (!this.converter.isEmpty()) {
            NSWF_Image get = (NSWF_Image) this.converter.get(this.displayed_index);
            List<NSFW_BoundingBox> results = get.getResults();
            int x = evt.getX();
            int y = evt.getY();

            Rectangle viewportsize = this.originalImageLabel.getBounds();
            BufferedImage org = get.getBufferedImage();
            double scalex = (double) this.originalImageLabel.getWidth() / (double) org.getWidth();
            double scaley = (double) this.originalImageLabel.getHeight() / (double) org.getHeight();
            double scale = Math.min(scalex, scaley);
            int r_h = (int) ((double) org.getHeight() * scale);
            int r_w = (int) ((double) org.getWidth() * scale);
            int image_offset_y = (viewportsize.height - r_h) / 2;
            int image_offset_x = (viewportsize.width - r_w) / 2;

            for (int i = 0; i < results.size(); ++i) {
                NSFW_BoundingBox get1 = (NSFW_BoundingBox) results.get(i);
                Rectangle bounding_box = get1.bounding_box;
                int bx = (int) (scale * (double) bounding_box.x) + image_offset_x;
                int by = (int) (scale * (double) bounding_box.y) + image_offset_y;
                int bw = (int) (scale * (double) (bounding_box.width + bounding_box.x)) + image_offset_x;
                int bh = (int) (scale * (double) (bounding_box.height + bounding_box.y)) + image_offset_y;
                if (bx <= x && bw >= x && by <= y && bh >= y) {
                    this.jPopupMenu1.show(component, evt.getX(), evt.getY());
                    String var26 = get1.getName();
                    byte var27 = -1;
                    switch (var26.hashCode()) {
                        case -2078063293:
                            if (var26.equals("Face - Male")) {
                                var27 = 19;
                            }
                            break;
                        case -2068824744:
                            if (var26.equals("Male Genitalia - Exposed")) {
                                var27 = 7;
                            }
                            break;
                        case -1899816527:
                            if (var26.equals("Feet - Covered")) {
                                var27 = 13;
                            }
                            break;
                        case -1442154556:
                            if (var26.equals("Female Breast - Covered")) {
                                var27 = 9;
                            }
                            break;
                        case -1386400489:
                            if (var26.equals("Female Genitalia - Exposed")) {
                                var27 = 11;
                            }
                            break;
                        case -1336796209:
                            if (var26.equals("Stomach / Belly - Covered")) {
                                var27 = 1;
                            }
                            break;
                        case -1164361949:
                            if (var26.equals("Male Breast - Covered")) {
                                var27 = 5;
                            }
                            break;
                        case -552549023:
                            if (var26.equals("Armpits - Exposed")) {
                                var27 = 14;
                            }
                            break;
                        case -417915432:
                            if (var26.equals("Anus - Exposed")) {
                                var27 = 17;
                            }
                            break;
                        case -263522926:
                            if (var26.equals("Buttocks - Covered")) {
                                var27 = 3;
                            }
                            break;
                        case -55712510:
                            if (var26.equals("Face - Female")) {
                                var27 = 18;
                            }
                            break;
                        case 127610939:
                            if (var26.equals("Feet - Exposed")) {
                                var27 = 12;
                            }
                            break;
                        case 198715086:
                            if (var26.equals("Male Genitalia - Covered")) {
                                var27 = 6;
                            }
                            break;
                        case 585272910:
                            if (var26.equals("Female Breast - Exposed")) {
                                var27 = 8;
                            }
                            break;
                        case 690631257:
                            if (var26.equals("Stomach / Belly - Exposed")) {
                                var27 = 0;
                            }
                            break;
                        case 863065517:
                            if (var26.equals("Male Breast - Exposed")) {
                                var27 = 4;
                            }
                            break;
                        case 881139341:
                            if (var26.equals("Female Genitalia - Covered")) {
                                var27 = 10;
                            }
                            break;
                        case 1714990807:
                            if (var26.equals("Armpits - Covered")) {
                                var27 = 15;
                            }
                            break;
                        case 1763904540:
                            if (var26.equals("Buttocks - Exposed")) {
                                var27 = 2;
                            }
                            break;
                        case 1849624398:
                            if (var26.equals("Anus - Covered")) {
                                var27 = 16;
                            }
                    }

                    switch (var27) {
                        case 0:
                            this.belly_e_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 1:
                            this.belly_c_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 2:
                            this.buttocks_e_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 3:
                            this.buttocks_c_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 4:
                            this.mbreast_e_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 5:
                            this.mbreast_c_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 6:
                            this.mgen_c_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 7:
                            this.mgen_e_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 8:
                            this.fbreast_e_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 9:
                            this.fbreast_c_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 10:
                            this.fgen_c_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 11:
                            this.fgen_e_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 12:
                            this.fe_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 13:
                            this.fc_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 14:
                            this.ae_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 15:
                            this.ac_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 16:
                            this.anc_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 17:
                            this.ane_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 18:
                            this.ff_RadioButtonMenuItem.setSelected(true);
                            break;
                        case 19:
                            this.fm_RadioButtonMenuItem.setSelected(true);
                    }

                    this.pop_selection = get1;
                    return true;
                }
            }
        }

        return false;
    }

    private void originialimagelabelMouseDragged(MouseEvent evt) {
        this.mouse_m_x = evt.getX();
        this.mouse_m_y = evt.getY();
        if (this.pressed != null) {
            NSWF_Image get = (NSWF_Image) this.converter.get(this.displayed_index);
            BufferedImage bufferedImage = get.getBufferedImage();
            double scalex = (double) this.originalImageLabel.getWidth() / (double) bufferedImage.getWidth();
            double scaley = (double) this.originalImageLabel.getHeight() / (double) bufferedImage.getHeight();
            double scale = 1.0D / Math.min(scalex, scaley);
            int deltaX = (int) ((double) evt.getXOnScreen() - this.pressed_event.getX());
            int deltaY = (int) ((double) evt.getYOnScreen() - this.pressed_event.getY());
            this.pressed.getBounding_box().setLocation(Math.min(bufferedImage.getWidth() - this.pressed.getBounding_box().width, Math.max(0, this.p_x + (int) (scale * (double) deltaX))), Math.min(bufferedImage.getHeight() - this.pressed.getBounding_box().height, Math.max(0, this.p_y + (int) (scale * (double) deltaY))));
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }

            this.display(this.displayed_index);
        } else {
            if (this.new_bb != null) {
                this.display(this.displayed_index);
            }

        }
    }

    private void originialimagelabelMousePressed(MouseEvent evt) {
        del_buf = null;
        if (!this.converter.isEmpty()) {
            NSWF_Image get = (NSWF_Image) this.converter.get(this.displayed_index);
            List<NSFW_BoundingBox> results = get.getResults();
            int x = evt.getX();
            int y = evt.getY();

            Rectangle viewportsize = this.originalImageLabel.getBounds();
            BufferedImage org = get.getBufferedImage();
            double scalex = (double) this.originalImageLabel.getWidth() / (double) org.getWidth();
            double scaley = (double) this.originalImageLabel.getHeight() / (double) org.getHeight();
            double scale = Math.min(scalex, scaley);
            int r_h = (int) ((double) org.getHeight() * scale);
            int r_w = (int) ((double) org.getWidth() * scale);
            int image_offset_y = (viewportsize.height - r_h) / 2;
            int image_offset_x = (viewportsize.width - r_w) / 2;

            for (int i = 0; i < results.size(); ++i) {
                NSFW_BoundingBox get1 = (NSFW_BoundingBox) results.get(i);
                Rectangle bounding_box = get1.bounding_box;
                int bx = (int) (scale * (double) bounding_box.x) + image_offset_x;
                int by = (int) (scale * (double) bounding_box.y) + image_offset_y;
                int bw = (int) (scale * (double) (bounding_box.width + bounding_box.x)) + image_offset_x;
                int bh = (int) (scale * (double) (bounding_box.height + bounding_box.y)) + image_offset_y;
                if (bx <= x && bw >= x && by <= y && bh >= y) {
                    this.originalImageLabel.setCursor(Cursor.getPredefinedCursor(13));
                    this.pressed = get1;
                    del_buf = get1;
                    if (evt.isPopupTrigger() && this.doPopup(this.originalImageLabel, evt)) {
                        return;
                    }

                    this.pressed_event = evt.getLocationOnScreen();
                    this.p_x = get1.getBounding_box().x;
                    this.p_y = get1.getBounding_box().y;
                    return;
                }
            }
        }

        this.new_bb = evt.getLocationOnScreen();
        this.bb_x = evt.getX();
        this.bb_y = evt.getY();
    }

    private void originialimagelabelMouseReleased(MouseEvent evt) {
        if (!this.jPopupMenu1.isShowing()) {
            if (!evt.isPopupTrigger() || !this.doPopup(this.originalImageLabel, evt)) {
                this.pressed = null;
                if (this.new_bb != null && Math.abs(evt.getLocationOnScreen().x - this.new_bb.x) > 5 && Math.abs(evt.getLocationOnScreen().y - this.new_bb.y) > 5 && !this.converter.isEmpty()) {
                    NSWF_Image get = (NSWF_Image) this.converter.get(this.displayed_index);
                    List<NSFW_BoundingBox> results = get.getResults();
                    BufferedImage org = get.getBufferedImage();
                    double scalex = (double) this.originalImageLabel.getWidth() / (double) org.getWidth();
                    double scaley = (double) this.originalImageLabel.getHeight() / (double) org.getHeight();
                    double scale = Math.min(scalex, scaley);
                    int r_h = (int) ((double) org.getHeight() * scale);
                    int r_w = (int) ((double) org.getWidth() * scale);
                    int image_offset_y = (int) (1.0D / scale * (double) (this.originalImageLabel.getHeight() - r_h) / 2.0D);
                    int image_offset_x = (int) (1.0D / scale * (double) (this.originalImageLabel.getWidth() - r_w) / 2.0D);
                    int deltaX = Math.min(org.getWidth(), (int) (1.0D / scale * (double) (this.mouse_m_x - this.bb_x)));
                    int deltaY = Math.min(org.getHeight(), (int) (1.0D / scale * (double) (this.mouse_m_y - this.bb_y)));
                    int x = Math.min(org.getWidth(), Math.max(0, (int) (1.0D / scale * (double) this.bb_x) - image_offset_x));
                    int y = Math.min(org.getHeight(), Math.max(0, (int) (1.0D / scale * (double) this.bb_y) - image_offset_y));
                    if (deltaX > 0 && deltaY > 0) {
                        x = Math.min(org.getWidth(), Math.max(0, (int) (1.0D / scale * (double) this.bb_x) - image_offset_x));
                        y = Math.min(org.getHeight(), Math.max(0, (int) (1.0D / scale * (double) this.bb_y) - image_offset_y));
                    } else if (deltaX < 0 && deltaY < 0) {
                        x = Math.min(org.getWidth(), Math.max(0, (int) (1.0D / scale * (double) this.bb_x) - image_offset_x + deltaX));
                        y = Math.min(org.getHeight(), Math.max(0, (int) (1.0D / scale * (double) this.bb_y) - image_offset_y + deltaY));
                        deltaX = -deltaX;
                        deltaY = -deltaY;
                    } else if (deltaX < 0 && deltaY > 0) {
                        x = Math.min(org.getWidth(), Math.max(0, (int) (1.0D / scale * (double) this.bb_x) - image_offset_x + deltaX));
                        y = Math.min(org.getHeight(), Math.max(0, (int) (1.0D / scale * (double) this.bb_y) - image_offset_y));
                        deltaX = -deltaX;
                    } else if (deltaX > 0 && deltaY < 0) {
                        x = Math.min(org.getWidth(), Math.max(0, (int) (1.0D / scale * (double) this.bb_x) - image_offset_x));
                        y = Math.min(org.getHeight(), Math.max(0, (int) (1.0D / scale * (double) this.bb_y) - image_offset_y + deltaY));
                        deltaY = -deltaY;
                    }

                    if (Math.abs(deltaX * deltaY) > 20 && Math.abs(deltaX) > 5 && Math.abs(deltaY) > 5) {
                        results.add(new NSFW_BoundingBox("Stomach / Belly - Exposed", 1.0D, new int[]{x, y, deltaX + x, deltaY + y}));
                        if (this.editmode) {
                            ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
                        }
                    }

                    this.new_bb = null;
                    this.display(this.displayed_index);
                } else if (!this.converter.isEmpty()) {
                    this.display(this.displayed_index);
                }

                this.new_bb = null;
                this.originalImageLabel.setCursor(Cursor.getPredefinedCursor(0));
            }
        }
    }

    private void originialimagelabelMouseWheelMoved(MouseWheelEvent evt) {
        if (!this.converter.isEmpty()) {
            NSWF_Image get = (NSWF_Image) this.converter.get(this.displayed_index);
            List<NSFW_BoundingBox> results = get.getResults();
            int x = evt.getX();
            int y = evt.getY();

            Rectangle viewportsize = this.originalImageLabel.getBounds();
            BufferedImage org = get.getBufferedImage();
            double scalex = (double) this.originalImageLabel.getWidth() / (double) org.getWidth();
            double scaley = (double) this.originalImageLabel.getHeight() / (double) org.getHeight();
            double scale = Math.min(scalex, scaley);
            int r_h = (int) ((double) org.getHeight() * scale);
            int r_w = (int) ((double) org.getWidth() * scale);
            int image_offset_y = (viewportsize.height - r_h) / 2;
            int image_offset_x = (viewportsize.width - r_w) / 2;

            for (int i = 0; i < results.size(); ++i) {
                NSFW_BoundingBox get1 = (NSFW_BoundingBox) results.get(i);
                Rectangle bounding_box = get1.bounding_box;
                int bx = (int) (scale * (double) bounding_box.x) + image_offset_x;
                int by = (int) (scale * (double) bounding_box.y) + image_offset_y;
                int bw = (int) (scale * (double) (bounding_box.width + bounding_box.x)) + image_offset_x;
                int bh = (int) (scale * (double) (bounding_box.height + bounding_box.y)) + image_offset_y;
                if (bx <= x && bw >= x && by <= y && bh >= y) {
                    int nw = (int) (evt.getWheelRotation() > 0 ? (double) get1.bounding_box.width * 0.9D : (double) get1.bounding_box.width * 1.1D);
                    int nh = (int) (evt.getWheelRotation() > 0 ? (double) get1.bounding_box.height * 0.9D : (double) get1.bounding_box.height * 1.1D);
                    int nx = evt.getWheelRotation() < 0 ? get1.bounding_box.x - Math.abs(get1.bounding_box.width - nw) / 2 : get1.bounding_box.x + Math.abs(get1.bounding_box.width - nw) / 2;
                    int ny = evt.getWheelRotation() < 0 ? get1.bounding_box.y - Math.abs(get1.bounding_box.height - nh) / 2 : get1.bounding_box.y + Math.abs(get1.bounding_box.height - nh) / 2;
                    get1.bounding_box.setBounds(nx, ny, nw, nh);
                    this.display(this.displayed_index);
                    break;
                }
            }
        }

    }

    private void deleteMenuItemActionPerformed(ActionEvent evt) {
        NSWF_Image get = (NSWF_Image) this.converter.get(this.displayed_index);
        boolean remove = get.getResults().remove(this.pop_selection);
        this.display(this.displayed_index);
        if (this.editmode) {
            get.setEdited(true);
        }

    }

    private void belly_e_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Stomach / Belly - Exposed");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void belly_c_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Stomach / Belly - Covered");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void fbreast_c_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Female Breast - Covered");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void fbreast_e_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Female Breast - Exposed");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void buttocks_c_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Buttocks - Covered");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void buttocks_e_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Buttocks - Exposed");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void fgen_c_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Female Genitalia - Covered");
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void fgen_e_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Female Genitalia - Exposed");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void mbreast_c_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Male Breast - Covered");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void mbreast_e_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Male Breast - Exposed");
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void mgen_c_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Male Genitalia - Covered");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void mgen_e_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Male Genitalia - Exposed");
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void ff_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Face - Female");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void fm_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Face - Male");
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void jSpinner2StateChanged(ChangeEvent evt) {
        this.display(this.displayed_index);
    }

    private void fe_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Feet - Exposed");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void fc_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Feet - Covered");
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void ac_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Armpits - Covered");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void ae_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Armpits - Exposed");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void ane_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Anus - Exposed");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void anc_RadioButtonMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            this.pressed.setName("Anus - Covered");
            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }

    private void formWindowClosing(WindowEvent evt) {
    }

    private void jLabel12MouseClicked(MouseEvent evt) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Action.BROWSE)) {
            try {
                desktop.browse(new URI("https://www.patreon.com/puryfi"));
            } catch (URISyntaxException | IOException var4) {
                LOGGER.error((String) null, var4);
            }
        }

    }

    private void jLabel1MouseClicked(MouseEvent evt) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Action.BROWSE)) {
            try {
                desktop.browse(new URI("https://pury.fi/"));
            } catch (URISyntaxException | IOException var4) {
                LOGGER.error((String) null, var4);
            }
        }

    }

    private void labelmodeComboBoxActionPerformed(ActionEvent evt) {
        this.display(this.displayed_index);
    }

    private void editButtonActionPerformed(ActionEvent evt) {
        int dialogResult;
        if (!this.converter.isEmpty()) {
            int dialogButton = 0;
            dialogResult = JOptionPane.showConfirmDialog((Component) null, "This will clear all currently loaded data!\nWould you like to proceed?", "Warning", dialogButton);
        } else {
            dialogResult = 0;
        }

        if (dialogResult == 0) {
            this.converter.clear();
            File[] files = getFiles("output/source/", new String[]{".txt"});
            if (files != null) {
                this.editmode = true;

                for (int i = 0; i < files.length; ++i) {
                    File file = new File("output/source/" + files[i].getName().replace(".txt", ".png"));
                    if (file.exists()) {
                        NSWF_Image nswf_Image = new NSWF_Image(file, 1.0D, (JSONObject) null);
                        nswf_Image.setEditedsourcefileimage(file);
                        nswf_Image.setEditedsourcefiletxt(files[i]);
                        String[] readFile = readFile(files[i].getPath());

                        for (int j = 0; j < readFile.length; ++j) {
                            if (readFile[j] != null) {
                                String[] splited = readFile[j].split("\\s+");
                                int label = Integer.parseInt(splited[0]);
                                String name = NSFW_BoundingBox.getType(label).name();
                                double x_p = Double.parseDouble(splited[1]);
                                double y_p = Double.parseDouble(splited[2]);
                                double w_p = Double.parseDouble(splited[3]);
                                double h_p = Double.parseDouble(splited[4]);
                                int w = (int) (w_p * (double) nswf_Image.getBufferedImage().getWidth());
                                int h = (int) (h_p * (double) nswf_Image.getBufferedImage().getHeight());
                                int x = (int) (x_p * (double) nswf_Image.getBufferedImage().getWidth() - (double) (w / 2));
                                int y = (int) (y_p * (double) nswf_Image.getBufferedImage().getHeight() - (double) (h / 2));
                                int[] boxes = new int[]{x, y, w + x, h + y};
                                NSFW_BoundingBox box = new NSFW_BoundingBox(name, 1.0D, boxes);
                                nswf_Image.getResults().add(box);
                            }
                        }

                        this.converter.add(nswf_Image);
                        if (this.converter.size() == 1) {
                            this.display(this.converter.size() - 1);
                            this.saveButton.setText("Save images");
                            this.saveButton.setEnabled(true);
                        }

                        this.jLabel9.setText("Preview: " + (this.displayed_index + 1) + " / " + this.converter.size());
                    }
                }
            }
        }

    }

    private void stickerMenuItemActionPerformed(ActionEvent evt) {
        if (this.pressed != null) {
            if (this.pressed.getSticker() != null) {
                this.pressed.setSticker((BufferedImage) null);
                this.display(this.displayed_index);
                this.pressed = null;
                return;
            }

            Frame parent = new Frame();
            FileDialog fc = new FileDialog(parent, "Select Images", 0);
            fc.setDirectory("sticker");
            fc.setFile("*.jpg;*.jpeg;*.png");
            fc.setVisible(true);
            File[] files = fc.getFiles();
            if (files.length != 1) {
                this.pressed = null;
                return;
            }

            File file = files[0];

            try {
                BufferedImage read = ImageIO.read(file);
                this.pressed.setSticker(read);
            } catch (IOException var7) {
                LOGGER.error((String) null, var7);
            }

            if (this.editmode) {
                ((NSWF_Image) this.converter.get(this.displayed_index)).setEdited(true);
            }
        }

        this.display(this.displayed_index);
        this.pressed = null;
    }


}
