//Eclipse Jee 2019-06
package project3;
import weka.classifiers.*;
import weka.classifiers.bayes.NaiveBayes; //import NaiveBayes 
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.*; //import J48 
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class heartdisease{
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {		
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		
		DataSource source=new DataSource("C:\\Program Files\\Weka-3-9-4\\data\\heart.csv");
		Instances trainSet=source.getDataSet();
		trainSet.setClassIndex(trainSet.numAttributes()-1);
		
		NaiveBayes nB =new NaiveBayes();
		J48 tree= new J48();
		Logistic log = new Logistic();
		
		nB.buildClassifier(trainSet);
		tree.buildClassifier(trainSet);
		log.buildClassifier(trainSet);
		
		Evaluation evalTree = new Evaluation(trainSet);
		Evaluation evalNB = new Evaluation (trainSet);
		Evaluation evalLog = new Evaluation(trainSet);
		
		evalLog.crossValidateModel(log,trainSet,10, new Random(1));
		evalTree.crossValidateModel(tree, trainSet, 10,new Random(1));
		evalNB.crossValidateModel(nB, trainSet, 10,new Random(1));
		
		Attribute a1 = new Attribute("age");
		
		List<String> attr1 = new ArrayList<String>(2);	
		attr1.add("male");
		attr1.add("female");
		Attribute a2 = new Attribute("gender",attr1);
		
		List<String> attr2 = new ArrayList<String>(4);		//각 원소들 정의
		attr2.add("normal");
		attr2.add("angina");
		attr2.add("atypical_angina");
		attr2.add("asymptomatic");
		Attribute a3 = new Attribute("cp",attr2);
		
		Attribute a4 = new Attribute("trestbps");
		Attribute a5 = new Attribute("chol");
		
		List<String> attr3 = new ArrayList<String>(2);	
		attr3.add("TRUE");
		attr3.add("FALSE");
		Attribute a6 = new Attribute("fbs",attr3);
		
		List<String> attr4 = new ArrayList<String>(3);	
		attr4.add("normal");
		attr4.add("abnormal");
		attr4.add("L.V.H");
		Attribute a7 = new Attribute("restecg",attr4);
		
		Attribute a8 = new Attribute("thalach");
		
		List<String> attr5 = new ArrayList<String>(2);	
		attr5.add("yes");
		attr5.add("no");
		Attribute a9 = new Attribute("exang",attr5);
		
		Attribute a10 = new Attribute("oldpeak");
		
		List<String> attr6 = new ArrayList<String>(3);	
		attr6.add("up");
		attr6.add("down");
		attr6.add("flat");
		Attribute a11 = new Attribute("slope",attr6);
		
		Attribute a12 = new Attribute("ca");
		
		List<String> cls = new ArrayList<String>(2);
		cls.add("yes");
		cls.add("no");
		Attribute a13 = new Attribute("class",cls);
		
		ArrayList<Attribute> instanceAttributes = new ArrayList<Attribute>(13); //instance의 속성값 정의 + 속성값을 다 더해 instance의 속성값 집합을 만듦
		instanceAttributes.add(a1);
		instanceAttributes.add(a2);
		instanceAttributes.add(a3);
		instanceAttributes.add(a4);
		instanceAttributes.add(a5);
		instanceAttributes.add(a6);
		instanceAttributes.add(a7);
		instanceAttributes.add(a8);
		instanceAttributes.add(a9);
		instanceAttributes.add(a10);
		instanceAttributes.add(a11);
		instanceAttributes.add(a12);
		instanceAttributes.add(a13);
	
		
		while(true) {	//instance 입력
			String str; 
			String add;
		
			double atr1;
			System.out.print("age(29~77): ");
			atr1 = scan.nextDouble();
			double atr1_c=-0.0041;
			
			double atr2;
			double atr2_c;

			System.out.print("gender(male,female): ");
			str = scan.next();
			 if(str.equals("male")) {
		            atr2=0.0;
		            atr2_c=1.0;
		            } 
		     else if(str.equals("female")) {
		            atr2=1.0; 
		            atr2_c=2.0477;
		            }
		     else {
		            System.out.println("Wrong Input");
		            continue;
		         }

			double atr3;
			double atr3_c;
			System.out.print("cp(normal,angina,atypical_angina,asymptomatic): ");
			str = scan.next();
			if(str.equals("angina")) {
		        atr3=0.0;
		        atr3_c=1.0307;
		    }
		    else if(str.equals("normal")) {
		        atr3=1.0;
		        atr3_c=0.9163;
		    }
		    else if(str.equals("atypical_angina")) {
		        atr3=2.0;
		        atr3_c=0.116;
		    }
		    else if(str.equals("asymptomatic")) {
		        atr3=3.0;
		        atr3_c=-1.1068;
		    }
		    else {
		        System.out.println("Wrong Input");
		        continue;
		    }

			double atr4;
			double atr4_c=-0.0192;
			System.out.print("trestbps(94~200): ");
			atr4 = scan.nextDouble();
			
			double atr5;
			double atr5_c=-0.0059;
			System.out.print("chol(126~564): ");
			atr5 = scan.nextDouble();
			
			double atr6;
			double atr6_c;

			System.out.print("fbs(true,false): ");
			str = scan.next();
			if(str.equals("true")) {atr6=0.0;
	           atr6_c=1.0;
	        } 
	        else if(str.equals("false")) {atr6=1.0;
	         	atr6_c=-0.2839;
	        }
	        else {
	        	System.out.println("Wrong Input");
	            continue;
	        }

			double atr7;
			double atr7_c;
			System.out.print("restecg(normal,abnormal,LVH): ");
			str = scan.next();
			if(str.equals("LVH")) {atr7=0.0;
	         atr7_c=-0.1962;} 
	         else if(str.equals("normal")) {atr7=1.0;
	         atr7_c=0.2295;
	         }
	         else if(str.equals("abnormal")) {atr7=2.0;
	         atr7_c=-0.6409;
	         }else {
	            System.out.println("Wrong Input");
	            continue;
	         }

			
			double atr8;
			double atr8_c=0.0178;
			System.out.print("thalach(71~202): ");
			atr8 = scan.nextDouble();
			
			double atr9;
			double atr9_c;
			System.out.print("exang(yes,no): ");
			str = scan.next();
			if(str.equals("no")) {
				atr9=0.0;
				atr9_c=1.0;
				} 
	        else if(str.equals("yes")) {
	        	atr9=1.0;
	        	atr9_c=-9.022;
	        }
	        else {
	            System.out.println("Wrong Input");
	            continue;
	        }

			
			double atr10;
			double atr10_c=-0.5046;
			System.out.print("oldpeak(0.0~6.2): ");
			atr10 = scan.nextDouble();
			
			double atr11;
			double atr11_c;
			System.out.print("slope(up,down,flat): ");
			str = scan.next();
			if(str.equals("down")) {
				atr11=0.0; 
	         	atr11_c=0.3104;
	         	}
	        else if(str.equals("up")) {
	        	atr11=1.0;
	         	atr11_c=0.4837;
	        }
	        else if(str.equals("flat")) {
	        	atr11=2.0;
	        	atr11_c=-0.5651;
	        }
	        else {
	            System.out.println("Wrong Input");
	            continue;
	        }

			
			double atr12;
			double atr12_c=-0.8115;
			System.out.print("ca(0~4): ");
			atr12 = scan.nextDouble();
		
			Instances testSet = new Instances("testSet",instanceAttributes, 0); 	//testSet이라는 instance의 속성들을 가지는 임시 instance 생성
			testSet.setClassIndex(testSet.numAttributes()-1);						//class 지정
		
			double[] testData = new double []{
					atr1,atr2,atr3,atr4,atr5,atr6,atr7,atr8,atr9,atr10,atr11,atr12
			};
		
			Instance testInstance = new DenseInstance(1.0, testData);				//입력받은 속성들을 모두 합쳐서 instance로 만듦
			testSet.add(testInstance);												//testSet에 instance 속성들을 대입
			
			double logER = 1- evalLog.errorRate();
			double treeER = 1-evalTree.errorRate();//error rate
			double nBER = 1-evalNB.errorRate();
			
			double result1,result2,result3;
			if(logER>=treeER && logER >=nBER) {
				 result1=2.8952+atr1*atr1_c+atr2*atr2_c+atr3*atr3_c+atr4*atr4_c+atr5*atr5_c+atr6*atr6_c+atr7*atr7_c+atr8*atr8_c+atr9*atr9_c+atr10*atr10_c+atr11*atr11_c+atr12*atr12_c;
		         double p=Math.exp(result1);
		         double x=p/(p+1);
		      
		         //System.out.println(x);
		         if(x>0.5)
		            System.out.println("Logistic_target(accuracy: 84.44%): no");
		         else
		            System.out.println("Logistic_target(accuracy: 84.44%): yes");
		         
				result2 = nB.classifyInstance(testSet.instance(testSet.size()-1));
				if(result2 == 0) {
					System.out.println("Naive_Bayes_target(accuracy: 83.33%): yes");
				}
				else {
					System.out.println("Naive_Bayes_target(accuracy: 83.33%): no");
				}
			}
				result3 = tree.classifyInstance(testSet.instance(testSet.size()-1));
				if(result3 == 0) {
					System.out.println("J48_target(accuracy: 79.63%): yes");
				}
				else {
					System.out.println("J48_target(accuracy: 79.63%): no");
				}
				
				System.out.print("test case 추가 입력: (y/n)");
				add = scan.next();
				if(add.equals("n"))
					break;

			}		
		}
	}
}