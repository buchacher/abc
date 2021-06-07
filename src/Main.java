import mains.Puzzle;
import part3.DeclarativeABC;
import tests.SamplePuzzles;
import tests.TestsStaff;
import part4.HintABC;

public class Main {

    public static void printUsage() { 
        System.out.println("Input not recognised.  Usage is:");
        System.out.println("java A2main TEST|SOLVE|HINT <TestSet or Puzzle>"  );
        System.exit(1);
    }

    public static boolean checkPuzzleName(String name) { 
        for(SamplePuzzles p : SamplePuzzles.values() ) { 
            if(p.name().equals(name)) { return true; } ;
        } 
        return false; 
    }

	public static void main(String[] args) {
       
        if(args.length < 2) { printUsage(); };

		switch (args[0]) {
            //
            // Add additional commands if you wish 
            //
		case "TEST":
			switch (args[1]) {

                // Please add other test sets you construct yourself

                case "Staff1" : 
                    TestsStaff.part1();
                    return;

                case "Staff2" : 
                    TestsStaff.part2();
                    return;

                case "Staff3" : 
                    TestsStaff.part3();
                    return;

                case "StaffAll" : 
                    TestsStaff.part1();
                    TestsStaff.part2();
                    TestsStaff.part3();
                    return;

                default:
                    System.out.println("Test set not recognised");
			        return;
            }

        case "SOLVE": {
            if(checkPuzzleName(args[1])) {
                DeclarativeABC abc = new DeclarativeABC();
                Puzzle p = SamplePuzzles.valueOf(args[1]).puzzle;
                abc.setup(p);
                abc.createClauses();
                boolean result = abc.solvePuzzle();
                if (result) { 
                    p.printPuzzleGrid(abc.modelToGrid());
                }
                else if (abc.isUnknown()) { 
                    System.out.println(args[1]+" is unknown"); 
                }
                else { 
                    System.out.println(args[1]+" is unsolvable"); 
                }
            }
            else { System.out.println("Unknown puzzle " + args[1]); }
            return;
        }


        case "HINT": 
            if(checkPuzzleName(args[1])) {
                Puzzle p = SamplePuzzles.valueOf(args[1]).puzzle;
                HintABC.giveHints(p);
            }
            else { System.out.println("Unknown puzzle " + args[1]); }
            return;

        default : 
            printUsage(); 
            return;

		}

	}
}
