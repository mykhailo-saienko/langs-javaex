// Generated from JavaHeaderParser.g4 by ANTLR 4.6

	package ms.vm.lang.javaex.gen;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JavaHeaderParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JavaHeaderParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(JavaHeaderParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#packageDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageDeclaration(JavaHeaderParser.PackageDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#packageName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageName(JavaHeaderParser.PackageNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(JavaHeaderParser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#packageOrTypeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageOrTypeName(JavaHeaderParser.PackageOrTypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#importDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDeclaration(JavaHeaderParser.ImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#singleTypeImportDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleTypeImportDeclaration(JavaHeaderParser.SingleTypeImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#typeImportOnDemandDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeImportOnDemandDeclaration(JavaHeaderParser.TypeImportOnDemandDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#singleStaticImportDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleStaticImportDeclaration(JavaHeaderParser.SingleStaticImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#staticImportOnDemandDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStaticImportOnDemandDeclaration(JavaHeaderParser.StaticImportOnDemandDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#typeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclaration(JavaHeaderParser.TypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(JavaHeaderParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#bracketedBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketedBody(JavaHeaderParser.BracketedBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(JavaHeaderParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#actualPrimType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualPrimType(JavaHeaderParser.ActualPrimTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#referenceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceType(JavaHeaderParser.ReferenceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(JavaHeaderParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#typeVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeVariable(JavaHeaderParser.TypeVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#classType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassType(JavaHeaderParser.ClassTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassOrInterfaceType(JavaHeaderParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#classType_lfno_classOrInterfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassType_lfno_classOrInterfaceType(JavaHeaderParser.ClassType_lfno_classOrInterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#dims}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDims(JavaHeaderParser.DimsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#typeParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameter(JavaHeaderParser.TypeParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#typeParameterModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameterModifier(JavaHeaderParser.TypeParameterModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#typeBound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeBound(JavaHeaderParser.TypeBoundContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#additionalBound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionalBound(JavaHeaderParser.AdditionalBoundContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#typeArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArguments(JavaHeaderParser.TypeArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#typeArgumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArgumentList(JavaHeaderParser.TypeArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#typeArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArgument(JavaHeaderParser.TypeArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#wildcard}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildcard(JavaHeaderParser.WildcardContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#wildcardBounds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildcardBounds(JavaHeaderParser.WildcardBoundsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(JavaHeaderParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#enumDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumDeclaration(JavaHeaderParser.EnumDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#normalInterfaceDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalInterfaceDeclaration(JavaHeaderParser.NormalInterfaceDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationTypeDeclaration(JavaHeaderParser.AnnotationTypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#classIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassIdentifier(JavaHeaderParser.ClassIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#interfaceModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceModifier(JavaHeaderParser.InterfaceModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#classModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassModifier(JavaHeaderParser.ClassModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#typeParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameters(JavaHeaderParser.TypeParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#superclass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperclass(JavaHeaderParser.SuperclassContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#superinterfaces}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperinterfaces(JavaHeaderParser.SuperinterfacesContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#extendsInterfaces}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendsInterfaces(JavaHeaderParser.ExtendsInterfacesContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(JavaHeaderParser.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#normalAnnotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalAnnotation(JavaHeaderParser.NormalAnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#markerAnnotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMarkerAnnotation(JavaHeaderParser.MarkerAnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#singleElementAnnotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleElementAnnotation(JavaHeaderParser.SingleElementAnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#elementValuePairList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValuePairList(JavaHeaderParser.ElementValuePairListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#elementValuePair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValuePair(JavaHeaderParser.ElementValuePairContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaHeaderParser#elementValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValue(JavaHeaderParser.ElementValueContext ctx);
}