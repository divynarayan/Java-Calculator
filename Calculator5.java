import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator5 
{
	JFrame fr=new JFrame("Calculator");
	JTextField tb=new JTextField("0");
	JButton [] bt=new JButton[20];
	JPanel pa=new JPanel();
	JPanel pam=new JPanel();
	JLabel la=new JLabel();
	public Calculator5()
	{
		fr.setSize(400,450);
		fr.setDefaultCloseOperation(3);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setLayout(null);
		addTextBox();
		fr.setVisible(true);
	}
	private void addTextBox()
	{
		pam.setBounds(20,10,355,30);
		fr.add(pam);
		pam.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pam.add(la);
		tb.setBounds(20,40,355,40);
		tb.setFont(new Font("arial",0,22));
		tb.setHorizontalAlignment(4);//4:right,0:center,2:left
		tb.setEditable(false);
		tb.setBackground(Color.white);
		tb.setBorder(BorderFactory.createLineBorder(Color.black,1));
		fr.add(tb);
		addButtons();
	}
	private void addButtons()
	{
		pa.setBounds(20,100,355,310);
		//pa.setBackground(Color.yellow);
		fr.add(pa);
		pa.setLayout(new GridLayout(5,4,5,5));
		Font fo=new Font("arial",0,20);
		String[] str= {"Back","CE","C","%","7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
		CalListener listener=new CalListener();
		for(int i=0;i<20;i++)
		{
			bt[i]=new JButton(str[i]);
			bt[i].addActionListener(listener);
			bt[i].setFont(fo);
			if(i==3 || i==7 || i==11 || i==15 || i==18 || i==19)
				bt[i].setForeground(Color.red);
			else
				bt[i].setForeground(Color.blue);
			pa.add(bt[i]);
		}
		bt[17].setFont(new Font("arial",1,25));
	}
	class CalListener implements ActionListener
	{
		int o=0;
		float num1;
		String op=null;
		boolean equalClicked=false;
		public void actionPerformed(ActionEvent evt)
		{
			JButton bb=(JButton)evt.getSource();
			String v1=bb.getText();
			String v2=tb.getText();
			if(bb==bt[17])//. button
			{
				String str=tb.getText();
				int i=str.indexOf('.');
				if(i!=-1)
					return;
			}
			if(bb==bt[7]||bb==bt[11]||bb==bt[15]||bb==bt[19])
			{
				if(equalClicked)
				{
					equalClicked=false;
				}
				if(op!=null)
					cal();
				o=1;
				num1=Float.parseFloat(tb.getText());
				op=v1;
				la.setText(tb.getText()+" "+op);
			}
			if(bb==bt[4]||bb==bt[5]||bb==bt[6]||bb==bt[8]||bb==bt[9]||bb==bt[10]||bb==bt[12]||bb==bt[13]||bb==bt[14]||bb==bt[16]||bb==bt[17]) 
			{
				if(v2.equals("0") || o==1)
				{
					tb.setText(v1);
					if(equalClicked)
					{
						la.setText("");
						equalClicked=false;
					}
					o=0;
				}
				else
				{
					tb.setText(v2+v1);
				}
			}
			if(bb==bt[18]) //equal button
			{
				la.setText(la.getText()+" "+tb.getText()+" =");
				cal();
				o=1;
				equalClicked=true;
			}
			if(bb==bt[2]) //C button
			{
				tb.setText("0");
				op=null;
				o=0;
				num1=0;
				la.setText("");
			}
			if(bb==bt[1]) //CE button
			{
				tb.setText("0");
			}
			if(bb==bt[0]) //Back button
			{
				if(v2.equals("0"))
					return;
				int num=Integer.parseInt(v2);
				num=num/10;
				tb.setText(String.valueOf(num));
			}
		}//End of actionPerformed()
		private void cal()
		{
			float num2=Float.parseFloat(tb.getText());
			if(op.equals("+"))
			{
				float res=num1+num2;
				setResult(res);
			}
			else if(op.equals("*"))
			{
				float res=num1*num2;
				setResult(res);
			}
			else if(op.equals("/"))
			{
				float res=num1/num2;
				setResult(res);
			}
			else if(op.equals("-"))
			{
				float res=num1-num2;
				setResult(res);
			}
		}//End of cal()
		private void setResult(float res)
		{
			int x=(int)res;
			if((float)x==res)
				tb.setText(String.valueOf(x));
			else
				tb.setText(String.valueOf(res));
		}
	}//End of CalListener
	public static void main(String[] args) 
	{
		new Calculator5();
	}
}
