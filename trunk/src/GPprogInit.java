import java.util.LinkedList;
import java.util.Stack;

public class GPprogInit implements GPprogInitAPI {
	
	public LinkedList<Integer> gen_code = new LinkedList<Integer>();
	public Stack<Integer> grammarstack = new Stack<Integer>();
	
	public LinkedList<Integer> generate(){
		int i = 0;
		int time = 1;
		grammarstack.push(exp);
		
		while(!grammarstack.empty()){
			//<exp> -> <op-exp> | <action> | <sensor>
			if(grammarstack.peek() == 19){
				System.out.print("getin exp\n");
				grammarstack.pop();
				
				if(time == 1)
					i = 1;
				else if (gen_code.size()>=100)
					i = (int) Math.round(Math.random()+2);
				else
					i = (int)(Math.random()*3+1);
				
				switch(i){
					case 1:
						grammarstack.push(op_exp);
						break;
					case 2:
						grammarstack.push(action);
						break;
					case 3:
						grammarstack.push(sensor);
						break;
				}
			}
			
			//<op-exp> -> <if-exp> | <and-exp> | <or-exp> | <not-exp>
			else if(grammarstack.peek() == 20){
				System.out.print("getin on_exp\n");
				grammarstack.pop();
				i = (int)(Math.random()*4+1);
					switch(i){
					case 1:
						grammarstack.push(if_exp);
						break;
					case 2:
						grammarstack.push(and_exp);
						break;
					case 3:
						grammarstack.push(or_exp);
						break;
					case 4:
						grammarstack.push(not_exp);
						break;	
				}
			}
			//<action> -> moveE | moveW | moveN | moveS
			else if(grammarstack.peek() == 21){
				System.out.print("getin action\n");
				grammarstack.pop();
				i = (int)(Math.random()*4+1);
				switch(i){
				case 1:
					gen_code.add(moveE);
					break;
				case 2:
					gen_code.add(moveW);
					break;
				case 3:
					gen_code.add(moveN);
					break;
				case 4:
					gen_code.add(moveS);
					break;	
				}
			}
			//<sensor> -> n | s | w | e | ne | se | nw | sw |0 | 1
			else if(grammarstack.peek() == 22){
				System.out.print("getin sensor\n");
				grammarstack.pop();
				i = (int)(Math.random()*8+1);
				switch(i){
				case 1:
					gen_code.add(n);
					break;
				case 2:
					gen_code.add(s);
					break;
				case 3:
					gen_code.add(w);
					break;
				case 4:
					gen_code.add(e);
					break;	
				case 5:
					gen_code.add(ne);
					break;
				case 6:
					gen_code.add(se);
					break;
				case 7:
					gen_code.add(nw);
					break;
				case 8:
					gen_code.add(sw);
					break;
				case 9:
					gen_code.add(0);
					break;
				case 10:
					gen_code.add(1);
					break;				
				}
			}
			//<if-exp> -> IF <exp> <exp> <exp>
			else if(grammarstack.peek() == 23){
				System.out.print("getin if\n");
				grammarstack.pop();
				grammarstack.push(exp);
				grammarstack.push(exp);
				grammarstack.push(exp);
				gen_code.add(IF);
			}
			//<and-exp> -> AND <exp> <exp>
			else if(grammarstack.peek() == 24){
				System.out.print("getin and\n");
				grammarstack.pop();
				grammarstack.push(exp);
				grammarstack.push(exp);
				gen_code.add(AND);
			}
			//<or-exp> -> OR <exp> <exp>
			else if(grammarstack.peek() == 25){
				System.out.print("getin or\n");
				grammarstack.pop();
				grammarstack.push(exp);
				grammarstack.push(exp);
				gen_code.add(OR);
			}
			//<not-exp>-> NOT <exp>
			else if(grammarstack.peek() == 26){
				System.out.print("getin not\n");
				grammarstack.pop();
				grammarstack.push(exp);
				gen_code.add(NOT);
			}	
			time++;
	}
		/*
		System.out.print("link size:"+gen_code.size()+"\n");
		while(!gen_code.isEmpty()){
			System.out.print(gen_code.poll()+"\n");
		}
		*/
		return gen_code;
	}

}