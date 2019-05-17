// Generated from JavaHeaderParser.g4 by ANTLR 4.6

	package ms.vm.lang.javaex.gen;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JavaHeaderParser}.
 */
public interface JavaHeaderParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(JavaHeaderParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(JavaHeaderParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPackageDeclaration(JavaHeaderParser.PackageDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPackageDeclaration(JavaHeaderParser.PackageDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#packageName}.
	 * @param ctx the parse tree
	 */
	void enterPackageName(JavaHeaderParser.PackageNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#packageName}.
	 * @param ctx the parse tree
	 */
	void exitPackageName(JavaHeaderParser.PackageNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(JavaHeaderParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(JavaHeaderParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#packageOrTypeName}.
	 * @param ctx the parse tree
	 */
	void enterPackageOrTypeName(JavaHeaderParser.PackageOrTypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#packageOrTypeName}.
	 * @param ctx the parse tree
	 */
	void exitPackageOrTypeName(JavaHeaderParser.PackageOrTypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterImportDeclaration(JavaHeaderParser.ImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitImportDeclaration(JavaHeaderParser.ImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#singleTypeImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSingleTypeImportDeclaration(JavaHeaderParser.SingleTypeImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#singleTypeImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSingleTypeImportDeclaration(JavaHeaderParser.SingleTypeImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#typeImportOnDemandDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeImportOnDemandDeclaration(JavaHeaderParser.TypeImportOnDemandDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#typeImportOnDemandDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeImportOnDemandDeclaration(JavaHeaderParser.TypeImportOnDemandDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#singleStaticImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSingleStaticImportDeclaration(JavaHeaderParser.SingleStaticImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#singleStaticImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSingleStaticImportDeclaration(JavaHeaderParser.SingleStaticImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#staticImportOnDemandDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStaticImportOnDemandDeclaration(JavaHeaderParser.StaticImportOnDemandDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#staticImportOnDemandDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStaticImportOnDemandDeclaration(JavaHeaderParser.StaticImportOnDemandDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(JavaHeaderParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(JavaHeaderParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(JavaHeaderParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(JavaHeaderParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#bracketedBody}.
	 * @param ctx the parse tree
	 */
	void enterBracketedBody(JavaHeaderParser.BracketedBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#bracketedBody}.
	 * @param ctx the parse tree
	 */
	void exitBracketedBody(JavaHeaderParser.BracketedBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(JavaHeaderParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(JavaHeaderParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#actualPrimType}.
	 * @param ctx the parse tree
	 */
	void enterActualPrimType(JavaHeaderParser.ActualPrimTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#actualPrimType}.
	 * @param ctx the parse tree
	 */
	void exitActualPrimType(JavaHeaderParser.ActualPrimTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#referenceType}.
	 * @param ctx the parse tree
	 */
	void enterReferenceType(JavaHeaderParser.ReferenceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#referenceType}.
	 * @param ctx the parse tree
	 */
	void exitReferenceType(JavaHeaderParser.ReferenceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(JavaHeaderParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(JavaHeaderParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void enterTypeVariable(JavaHeaderParser.TypeVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void exitTypeVariable(JavaHeaderParser.TypeVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#classType}.
	 * @param ctx the parse tree
	 */
	void enterClassType(JavaHeaderParser.ClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#classType}.
	 * @param ctx the parse tree
	 */
	void exitClassType(JavaHeaderParser.ClassTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterClassOrInterfaceType(JavaHeaderParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitClassOrInterfaceType(JavaHeaderParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#classType_lfno_classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterClassType_lfno_classOrInterfaceType(JavaHeaderParser.ClassType_lfno_classOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#classType_lfno_classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitClassType_lfno_classOrInterfaceType(JavaHeaderParser.ClassType_lfno_classOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#dims}.
	 * @param ctx the parse tree
	 */
	void enterDims(JavaHeaderParser.DimsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#dims}.
	 * @param ctx the parse tree
	 */
	void exitDims(JavaHeaderParser.DimsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameter(JavaHeaderParser.TypeParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameter(JavaHeaderParser.TypeParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#typeParameterModifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameterModifier(JavaHeaderParser.TypeParameterModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#typeParameterModifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameterModifier(JavaHeaderParser.TypeParameterModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#typeBound}.
	 * @param ctx the parse tree
	 */
	void enterTypeBound(JavaHeaderParser.TypeBoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#typeBound}.
	 * @param ctx the parse tree
	 */
	void exitTypeBound(JavaHeaderParser.TypeBoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#additionalBound}.
	 * @param ctx the parse tree
	 */
	void enterAdditionalBound(JavaHeaderParser.AdditionalBoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#additionalBound}.
	 * @param ctx the parse tree
	 */
	void exitAdditionalBound(JavaHeaderParser.AdditionalBoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void enterTypeArguments(JavaHeaderParser.TypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void exitTypeArguments(JavaHeaderParser.TypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#typeArgumentList}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgumentList(JavaHeaderParser.TypeArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#typeArgumentList}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgumentList(JavaHeaderParser.TypeArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgument(JavaHeaderParser.TypeArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgument(JavaHeaderParser.TypeArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#wildcard}.
	 * @param ctx the parse tree
	 */
	void enterWildcard(JavaHeaderParser.WildcardContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#wildcard}.
	 * @param ctx the parse tree
	 */
	void exitWildcard(JavaHeaderParser.WildcardContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#wildcardBounds}.
	 * @param ctx the parse tree
	 */
	void enterWildcardBounds(JavaHeaderParser.WildcardBoundsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#wildcardBounds}.
	 * @param ctx the parse tree
	 */
	void exitWildcardBounds(JavaHeaderParser.WildcardBoundsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(JavaHeaderParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(JavaHeaderParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#enumDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterEnumDeclaration(JavaHeaderParser.EnumDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#enumDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitEnumDeclaration(JavaHeaderParser.EnumDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#normalInterfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNormalInterfaceDeclaration(JavaHeaderParser.NormalInterfaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#normalInterfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNormalInterfaceDeclaration(JavaHeaderParser.NormalInterfaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeDeclaration(JavaHeaderParser.AnnotationTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeDeclaration(JavaHeaderParser.AnnotationTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#classIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterClassIdentifier(JavaHeaderParser.ClassIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#classIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitClassIdentifier(JavaHeaderParser.ClassIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#interfaceModifier}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceModifier(JavaHeaderParser.InterfaceModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#interfaceModifier}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceModifier(JavaHeaderParser.InterfaceModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#classModifier}.
	 * @param ctx the parse tree
	 */
	void enterClassModifier(JavaHeaderParser.ClassModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#classModifier}.
	 * @param ctx the parse tree
	 */
	void exitClassModifier(JavaHeaderParser.ClassModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameters(JavaHeaderParser.TypeParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameters(JavaHeaderParser.TypeParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#superclass}.
	 * @param ctx the parse tree
	 */
	void enterSuperclass(JavaHeaderParser.SuperclassContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#superclass}.
	 * @param ctx the parse tree
	 */
	void exitSuperclass(JavaHeaderParser.SuperclassContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#superinterfaces}.
	 * @param ctx the parse tree
	 */
	void enterSuperinterfaces(JavaHeaderParser.SuperinterfacesContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#superinterfaces}.
	 * @param ctx the parse tree
	 */
	void exitSuperinterfaces(JavaHeaderParser.SuperinterfacesContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#extendsInterfaces}.
	 * @param ctx the parse tree
	 */
	void enterExtendsInterfaces(JavaHeaderParser.ExtendsInterfacesContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#extendsInterfaces}.
	 * @param ctx the parse tree
	 */
	void exitExtendsInterfaces(JavaHeaderParser.ExtendsInterfacesContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(JavaHeaderParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(JavaHeaderParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#normalAnnotation}.
	 * @param ctx the parse tree
	 */
	void enterNormalAnnotation(JavaHeaderParser.NormalAnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#normalAnnotation}.
	 * @param ctx the parse tree
	 */
	void exitNormalAnnotation(JavaHeaderParser.NormalAnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#markerAnnotation}.
	 * @param ctx the parse tree
	 */
	void enterMarkerAnnotation(JavaHeaderParser.MarkerAnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#markerAnnotation}.
	 * @param ctx the parse tree
	 */
	void exitMarkerAnnotation(JavaHeaderParser.MarkerAnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#singleElementAnnotation}.
	 * @param ctx the parse tree
	 */
	void enterSingleElementAnnotation(JavaHeaderParser.SingleElementAnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#singleElementAnnotation}.
	 * @param ctx the parse tree
	 */
	void exitSingleElementAnnotation(JavaHeaderParser.SingleElementAnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#elementValuePairList}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePairList(JavaHeaderParser.ElementValuePairListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#elementValuePairList}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePairList(JavaHeaderParser.ElementValuePairListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePair(JavaHeaderParser.ElementValuePairContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePair(JavaHeaderParser.ElementValuePairContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaHeaderParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void enterElementValue(JavaHeaderParser.ElementValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaHeaderParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void exitElementValue(JavaHeaderParser.ElementValueContext ctx);
}