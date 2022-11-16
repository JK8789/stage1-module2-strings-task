package com.epam.mjc;

import java.util.*;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringTokenizer tokens = new StringTokenizer(signatureString, "()");
        String partBeforeArgs;
        String allArguments = null;
        partBeforeArgs = tokens.nextToken();
        if (tokens.hasMoreTokens()) {
            allArguments = tokens.nextToken();
        }
        assert partBeforeArgs != null : "Part Before Arguments is null";
        String[] splitPart = partBeforeArgs.split(" ");
        if (allArguments == null) {
            MethodSignature methodSignature = new MethodSignature(splitPart[splitPart.length - 1]);
            parsePartBeforeArgs(splitPart, methodSignature);
            return methodSignature;
        }
        String[] argumentsWithNAT = allArguments.split(", ");
        List<String> listOfArgsWithNAT = new ArrayList<>();
        Collections.addAll(listOfArgsWithNAT, argumentsWithNAT);
        List<MethodSignature.Argument> args = new ArrayList<>();
        for (int i = 0; i < listOfArgsWithNAT.size(); i++) {
            String[] nameAndTypeOfArg = listOfArgsWithNAT.get(i).split(" ");
            String type = nameAndTypeOfArg[0];
            String name = nameAndTypeOfArg[1];
            MethodSignature.Argument argument = new MethodSignature.Argument(type, name);
            args.add(i, argument);
        }
        MethodSignature methodSignature = new MethodSignature(splitPart[splitPart.length - 1], args);
        parsePartBeforeArgs(splitPart, methodSignature);
        return methodSignature;
    }

    private static void parsePartBeforeArgs(String[] splitPart, MethodSignature methodSignature) {
        if (splitPart.length == 3) {
            methodSignature.setAccessModifier(splitPart[splitPart.length - 3]);
        } else {
            methodSignature.setAccessModifier(null);
        }
        methodSignature.setReturnType(splitPart[splitPart.length - 2]);
    }
}
