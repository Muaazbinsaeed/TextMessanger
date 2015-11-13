import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.util.StringTokenizer;
import javax.swing.BoxLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.ScrollPaneConstants;
public class LoginPage 
{
	
	String UserName,Password;
	String Sentence;
	private JFrame frame;
	private JTextField textField_AddFriend;
	TCPClient Connect;
	JLabel[] OnlineFriendNamelabels;
	JLabel[] OfflineFriendNamelabels;
	SimpleAttributeSet style_sender_name,style_recver_name,style_msg;

	JPanel PaneUserHeadder;
	JLabel lblUserHeadder ;
	JTabbedPane tabbedPane;
	JPanel Tab_Friend_Panel;    
	JScrollPane ScrollPanel_Online_Friend;
    JPanel Panel_Online_Headding;
    JLabel lblOnlineHeadding;
    JPanel Panel_Online_Body;
    JScrollPane ScrollPane_Offline_Friends;
	JPanel Panel_Offine_Headding;
	JLabel lblOfflineHeadding;
	JPanel Panel_Offline_Body ;
	JPanel Tab_AddFriend_Panel;
	JLabel lblAddFriend ;
	JButton btn_AddFriend ;
	JButton btn_Multimedia_SendFile ;
	JButton btn_Multimedia_VideoCall ;
	JButton btn_Multimedia_AudioCall ;
	private JTextArea textArea_Chat_Send,textArea_btn_Multimedia_SendFile;
	private JPanel Tab_Chat_panel;
	private JScrollPane scrollPane_Chat_Chatting_Window;
	private JScrollPane scrollPane;
	JInternalFrame Chatting_Window ;
	JTextPane textArea_Chat_Chatting_Window;
	String FriendSender;
	
	Thread threadrcv;
	//t.start();
	
	int ChatTabNo;
	
	public LoginPage(String U,String P) throws Exception
	{
		UserName=U;
		Password=P;
		ChatTabNo=0;
		Connect = new TCPClient();
		SetFondStyle();

		initialize();
		
		//threadrcv = new ChatRecvThread();
		//threadrcv.start();
		
		/*
		threadrcv = new ChatRecvThread();
		threadrcv.start();
		initialize();
		*/
		
	}

	public void running() 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
     /*
	public static void main(String [] args)///////////////////////////////////////////////////
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try 
				{
					LoginPage window = new LoginPage("muaaz","a");////////////////
					window.frame.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */

	void SetFondStyle()
	{
		style_sender_name = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style_sender_name, "Harlow Solid Bold");
		StyleConstants.setFontSize(style_sender_name, 15);
		StyleConstants.setForeground(style_sender_name, Color.GREEN);
		
		style_recver_name = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style_recver_name, "Harlow Solid Bold");
		StyleConstants.setFontSize(style_recver_name, 15);
		StyleConstants.setForeground(style_recver_name, Color.BLUE);

		style_msg = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style_msg, "Arial Plane");
		StyleConstants.setFontSize(style_msg, 10);
		StyleConstants.setForeground(style_msg, Color.BLACK);
	}
	
	void waitOnlineFriendListLabel(String OnlineName)
	{

		try{
		StringTokenizer st = new StringTokenizer(OnlineName);
		OnlineFriendNamelabels = new JLabel[st.countTokens()];
		for(int i = 0;st.hasMoreTokens();i++) 
		{
			OnlineFriendNamelabels[i] = new JLabel(st.nextToken());
            OnlineFriendNamelabels[i].setCursor(Cursor.getPredefinedCursor(12));
            OnlineFriendNamelabels[i].setFont(new Font("Script MT Bold", Font.PLAIN, 20));            
            OnlineFriendNamelabels[i].addMouseListener(new MouseAdapter() 
            {
    			@Override
    			public void mouseClicked(MouseEvent arg0) 
    			{
    				String Frind =  ((JLabel) arg0.getSource()).getText();
    				try 
    				{
    					if(ChatTabNo == 0)
    					{
    						ChatTabNo++;
    					    SetChatting_Window(Frind);
    					}
    					else
    						System.out.println("Already Open Chat Box");//////////////////
					} 
    				catch (Exception e) 
					{

						e.printStackTrace();
					}						
    			}
    		});

        	Panel_Online_Body.add(OnlineFriendNamelabels[i]);
            //OnlineFriendNamelabels[i].setIcon(blank);
        }
		}
		catch(Exception ee)
		{ee.printStackTrace();}
	}

	void waitOfflineFriendListLabel(String OnlineName)
	{
		StringTokenizer st = new StringTokenizer(OnlineName);
		OfflineFriendNamelabels = new JLabel[st.countTokens()];
		System.out.println("FriendsList: "+st.countTokens());////////////////////
		for(int i = 0;st.hasMoreTokens();i++) 
		{
			OfflineFriendNamelabels[i] = new JLabel(st.nextToken());
			OfflineFriendNamelabels[i].setCursor(Cursor.getPredefinedCursor(12));
			OfflineFriendNamelabels[i].setFont(new Font("Script MT Bold", Font.PLAIN, 20));
			OfflineFriendNamelabels[i].addMouseListener(new MouseAdapter() 
            {
    			@Override
    			public void mouseClicked(MouseEvent arg0) 
    			{
    				/*
    				String Frind =  ((JLabel) arg0.getSource()).getText();
    				try 
    				{
    					if(ChatTabNo == 0)
    					{
    						ChatTabNo++;
    					    SetChatting_WindowWindow(Frind);
    					    Thread t = new ChatRecvThread(Frind);
    						t.start();
    					}
    					else
    						System.out.println("Already Open Chat Box");//////////////////
					} 
    				catch (Exception e) 
					{
						e.printStackTrace();
					}
					*/
    			}
    		});
            //OfflineFriendNamelabels[i].setIcon(blank);
        	Panel_Offline_Body.add(OfflineFriendNamelabels[i]);
        }
	}

	private void initialize() throws PropertyVetoException 
	{
		
		frame = new JFrame("NUCES Chat Services");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		threadrcv = new ChatRecvThread();
		threadrcv.start();
		
		SetUserHeadder();
		SetTabbedPane();
		SetTabAddFriend();
		requestFriendTab();
		//SetChatting_Window("Friend");///////////
	}

	void SetUserHeadder()
	{
		PaneUserHeadder = new JPanel();
		PaneUserHeadder.setBounds(0, 0, 484, 25);
		frame.getContentPane().add(PaneUserHeadder);
		PaneUserHeadder.setLayout(null);
		
		lblUserHeadder = new JLabel(UserName);
		lblUserHeadder.setFont(new Font("Script MT Bold", Font.BOLD, 18));
		lblUserHeadder.setBounds(43, 0, 148, 25);
		PaneUserHeadder.add(lblUserHeadder);
	}
	
	void SetTabbedPane()
	{
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 24, 474, 410);
		frame.getContentPane().add(tabbedPane);
	}
	
	void SetTabFriend()
	{
		Tab_Friend_Panel = new JPanel();	    
		tabbedPane.addTab("Friend", Tab_Friend_Panel);
		Tab_Friend_Panel.setLayout(null);
        
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0)
        	{
        		tabbedPane.remove(Tab_Friend_Panel);
        		requestFriendTab();
        	}
        });
        btnRefresh.setBounds(371, 0, 98, 23);
        Tab_Friend_Panel.add(btnRefresh);

        ScrollPanel_Online_Friend = new JScrollPane();
        ScrollPanel_Online_Friend.setBounds(10, 22, 409, 180);        
        
        Tab_Friend_Panel.add(ScrollPanel_Online_Friend);
        
        Panel_Online_Headding = new JPanel();
        ScrollPanel_Online_Friend.setColumnHeaderView(Panel_Online_Headding);
        
        lblOnlineHeadding = new JLabel("ONLINE");
        Panel_Online_Headding.add(lblOnlineHeadding);
        lblOnlineHeadding.setHorizontalAlignment(SwingConstants.CENTER);
        
        Panel_Online_Body = new JPanel();
        
        //Connect.FriendList("online",UserName, Password);
        //waitOnlineFriendListLabel();
        
        
        ScrollPanel_Online_Friend.setViewportView(Panel_Online_Body);
        Panel_Online_Body.setLayout(new BoxLayout(Panel_Online_Body, BoxLayout.Y_AXIS));

		ScrollPane_Offline_Friends = new JScrollPane();
		ScrollPane_Offline_Friends.setBounds(10, 224, 409, 133);

		Tab_Friend_Panel.add(ScrollPane_Offline_Friends);

		Panel_Offine_Headding = new JPanel();
		ScrollPane_Offline_Friends.setColumnHeaderView(Panel_Offine_Headding);
		Panel_Offine_Headding.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblOfflineHeadding = new JLabel("OFFLINE");
		Panel_Offine_Headding.add(lblOfflineHeadding);
		lblOfflineHeadding.setHorizontalAlignment(SwingConstants.CENTER);
		
		Panel_Offline_Body = new JPanel();
		Panel_Offline_Body.setLayout(new BoxLayout(Panel_Offline_Body, BoxLayout.Y_AXIS));
		

		//Connect.FriendList("offline",UserName, Password);
		//waitOfflineFriendListLabel();
		ScrollPane_Offline_Friends.setViewportView(Panel_Offline_Body);
	}
	
	void SetTabAddFriend()
	{
		Tab_AddFriend_Panel = new JPanel();
		tabbedPane.addTab("Find Friends",Tab_AddFriend_Panel);
		Tab_AddFriend_Panel.setLayout(null);
		
		lblAddFriend = new JLabel("FIND PERSON");
		lblAddFriend.setFont(new Font("Gabriola", Font.BOLD, 25));
		lblAddFriend.setBounds(94, 27, 151, 37);
		Tab_AddFriend_Panel.add(lblAddFriend);
		
		textField_AddFriend = new JTextField();
		textField_AddFriend.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_AddFriend.setBounds(94, 75, 224, 48);
		Tab_AddFriend_Panel.add(textField_AddFriend);
		textField_AddFriend.setColumns(10);
		
		btn_AddFriend = new JButton("Find");
		btn_AddFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!textField_AddFriend.getText().equals(""))
				{
					Connect.AddFriend(UserName,textField_AddFriend.getText());
					textField_AddFriend.setText("");
					//waitAddingFriend();
				}
			}
		});
		btn_AddFriend.setBounds(229, 156, 89, 23);
		Tab_AddFriend_Panel.add(btn_AddFriend);
	}
	void waitAddingFriend(String Sentence)
	{
		if(Sentence.equals("t"))
		{
			System.out.println("Friend Added");////////////////////
			tabbedPane.remove(Tab_Friend_Panel);
			requestFriendTab();
		}
		else if(Sentence.equals("alreadyexist"))
			System.out.println("Already Exist");////////////////////
		else
			System.out.println("People Not Found");////////////////////
	}

	void SetChatting_Window(final String Friend) throws PropertyVetoException
	{
		JButton btnExit;
		
		Tab_Chat_panel = new JPanel();
		tabbedPane.addTab("Chat", null, Tab_Chat_panel, null);
		Tab_Chat_panel.setLayout(null);
		
		btnExit = new JButton("X");
		btnExit.setBounds(411, 5, 48, 30);
		Tab_Chat_panel.add(btnExit);
		btnExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ExitChattingTab();
				Connect.sendonmsg(UserName, Friend, "?off?");
			}
		});
		Chatting_Window = new JInternalFrame(Friend);
		Chatting_Window.setClosable(true);
		Chatting_Window.setClosed(true);
		Chatting_Window.setBounds(0, 5, 469, 377);
		Tab_Chat_panel.add(Chatting_Window);
		Chatting_Window.setSelected(true);
		Chatting_Window.getContentPane().setLayout(null);
		
		SetChatting_panel_Multimedia(Friend);
		SetChatting_panel_Chat(Friend);
		
		Chatting_Window.setVisible(true);
	}

	void SetChatting_panel_Multimedia(final String Friend)
	{
		JPanel panel_Multimedia = new JPanel();
		panel_Multimedia.setBounds(0, 11, 453, 95);
		Chatting_Window.getContentPane().add(panel_Multimedia);
		panel_Multimedia.setLayout(null);
		
		btn_Multimedia_AudioCall = new JButton("Audio Call");
		btn_Multimedia_AudioCall.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
			}
		});
		btn_Multimedia_AudioCall.setBounds(164, 28, 131, 67);
		panel_Multimedia.add(btn_Multimedia_AudioCall);
		
		btn_Multimedia_VideoCall = new JButton("Video Call");
		btn_Multimedia_VideoCall.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
			}
		});
		btn_Multimedia_VideoCall.setBounds(10, 28, 119, 67);
		panel_Multimedia.add(btn_Multimedia_VideoCall);
		
		JScrollPane scrollPane_btn_Multimedia_SendFile = new JScrollPane();
		scrollPane_btn_Multimedia_SendFile.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_btn_Multimedia_SendFile.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_btn_Multimedia_SendFile.setBounds(310, 28, 133, 67);
		panel_Multimedia.add(scrollPane_btn_Multimedia_SendFile);
		
		textArea_btn_Multimedia_SendFile = new JTextArea();
		scrollPane_btn_Multimedia_SendFile.setViewportView(textArea_btn_Multimedia_SendFile);
		textArea_btn_Multimedia_SendFile.setColumns(10);
		
		btn_Multimedia_SendFile = new JButton("Send File");
		scrollPane_btn_Multimedia_SendFile.setColumnHeaderView(btn_Multimedia_SendFile);
		btn_Multimedia_SendFile.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					String filename = textArea_btn_Multimedia_SendFile.getText();
					String status = Connect.sendonfile(UserName,Friend,filename);
					System.out.println(status);////////////////////
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	void SetChatting_panel_Chat(final String Friend)
	{
		
		JPanel panel_Chat = new JPanel();
		panel_Chat.setBounds(0, 117, 453, 224);
		Chatting_Window.getContentPane().add(panel_Chat);
		panel_Chat.setLayout(null);
		
		scrollPane_Chat_Chatting_Window = new JScrollPane();
		scrollPane_Chat_Chatting_Window.setBounds(10, 0, 433, 185);
		panel_Chat.add(scrollPane_Chat_Chatting_Window);
		
		textArea_Chat_Chatting_Window = new JTextPane();
		scrollPane_Chat_Chatting_Window.setViewportView(textArea_Chat_Chatting_Window);
		textArea_Chat_Chatting_Window.setEditable(false);
		
		JButton btn_Chat_Send = new JButton("Send");
		btn_Chat_Send.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				OnlineChat(Friend);
			}
		});
		btn_Chat_Send.setBounds(354, 196, 89, 23);
		panel_Chat.add(btn_Chat_Send);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 196, 333, 23);
		panel_Chat.add(scrollPane);
		
		textArea_Chat_Send = new JTextArea();
		scrollPane.setViewportView(textArea_Chat_Send);
	}
	
	void OfflineChat(String Friend)
	{
		System.out.println("Name is: "+Friend);////////////////////
	}

	void OnlineChat(String Friend)
	{
			
		System.out.println("Name is: "+Friend);////////////////////
		
		String msg=textArea_Chat_Send.getText();
		if(! (msg.equals("")))
		{					
			try 
			{
				textArea_Chat_Chatting_Window.getDocument().insertString(textArea_Chat_Chatting_Window.getDocument().getLength(),"\n"+UserName+":", style_sender_name);
				textArea_Chat_Chatting_Window.getDocument().insertString(textArea_Chat_Chatting_Window.getDocument().getLength(),msg, style_msg);
				
				//textArea_Chat_Chatting_Window.getDocument().insertString(textArea_Chat_Chatting_Window.getCaretPosition(), "\n"+UserName+":", style_sender_name);
				//textArea_Chat_Chatting_Window.getDocument().insertString(textArea_Chat_Chatting_Window.getCaretPosition(),msg, style_msg);
				
				Connect.sendonmsg(UserName, Friend, msg);
				textArea_Chat_Send.setText("");
				
			} 
			catch (BadLocationException e) 
			{
				e.printStackTrace();
			}	
		}
	}
	
	class ChatRecvThread extends Thread
	{
		//String Sentence;

		
		public void run()
		{
			while(true)
			{
				try 
				{
					Connect.RcvHandler();
					//requestFriendTab();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	void waitsendonfile(String sender,String filename) throws PropertyVetoException
	{
		int newfriend=0;
		for(int i=0;i<tabbedPane.getTabCount();i++)
		{
			if( tabbedPane.getTitleAt(i).equals("Chat"))
			{
				if(!Chatting_Window.getTitle().equals(sender))
					newfriend=1;
				break;
			}
			else if((i == tabbedPane.getTabCount()-1) && (!tabbedPane.getTitleAt(i).equals("Chat")))
			{
				SetChatting_Window(sender);
			}
		}
		if(newfriend==1)
		{
			Connect.sendonmsg(UserName, sender, "?off?");
			System.out.println("Already Chatting");////////////////////
			return;
		}
		ChatTabNo=1;
		System.out.println("Name is: "+sender+";\tFile is: "+filename+";");////////////////////
		try 
		{
			textArea_Chat_Chatting_Window.getDocument().insertString(textArea_Chat_Chatting_Window.getDocument().getLength(),"\n"+sender+":", style_recver_name);
			textArea_Chat_Chatting_Window.getDocument().insertString(textArea_Chat_Chatting_Window.getDocument().getLength(),"Recived File:"+filename, style_msg);
		} 
		catch (BadLocationException e) 
		{
			e.printStackTrace();
		}
	}
	void waitsendonmsg(String [] cmd) throws PropertyVetoException
	{
		String sender;
		int newfriend=0;
		
		String msg = "";
		sender=cmd[1];
		for (int k=3; k < cmd.length; k++)
			msg += cmd[k]+" ";
		
		for(int i=0;i<tabbedPane.getTabCount();i++)
		{
			if( tabbedPane.getTitleAt(i).equals("Chat"))
			{
				if(!Chatting_Window.getTitle().equals(sender))
					newfriend=1;
				break;
			}
			else if((i == tabbedPane.getTabCount()-1) && (!tabbedPane.getTitleAt(i).equals("Chat")))
			{
				SetChatting_Window(sender);
			}
		}
		if(newfriend==1)
		{
			Connect.sendonmsg(UserName, sender, "?off?");
			System.out.println("Already Chatting");////////////////////
			return;
		}
		ChatTabNo=1;
		System.out.println("Name is: "+sender+";\tMsg is: "+msg+";");////////////////////
		if((msg.equals("?off? ")))
		{
			System.out.println("Closed by your Friend");////////////////////
			ExitChattingTab();
			return;
		}
		try 
		{
			textArea_Chat_Chatting_Window.getDocument().insertString(textArea_Chat_Chatting_Window.getDocument().getLength(),"\n"+sender+":", style_recver_name);
			textArea_Chat_Chatting_Window.getDocument().insertString(textArea_Chat_Chatting_Window.getDocument().getLength(),msg, style_msg);
			

			//textArea_Chat_Chatting_Window.getDocument().insertString(textArea_Chat_Chatting_Window.getCaretPosition(),sender+":", style_recver_name);
			//textArea_Chat_Chatting_Window.getDocument().insertString(textArea_Chat_Chatting_Window.getCaretPosition(),"\n"+msg, style_msg);
		} 
		catch (BadLocationException e) 
		{
			e.printStackTrace();
		}
	}

	void ExitChattingTab()
	{
		//tabbedPane.remove(panel);
		Chatting_Window.dispose();
		for(int i=0;i<tabbedPane.getTabCount();i++)
		{
			if(tabbedPane.getTitleAt(i).equals("Chat"))
				tabbedPane.removeTabAt(i);
		}
		ChatTabNo=0;
		////For Quiting Friend/////////////
	}
	void requestFriendTab()
	{
		SetTabFriend();	
		Connect.FriendList("online",UserName, Password);
		Connect.FriendList("offline",UserName, Password);
	}
}
