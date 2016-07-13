package org.hisrc.jsonix.compilation.mapping;

import org.apache.commons.lang3.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jsonix.definition.Modules;
import org.jvnet.jaxb2_commons.xml.bind.model.MPackagedTypeInfo;

public class PackagedTypeInfoCompiler<T, C extends T> implements TypeInfoCompiler<T, C> {

	private final MPackagedTypeInfo<T, C> typeInfo;

	public PackagedTypeInfoCompiler(MPackagedTypeInfo<T, C> typeInfo) {
		Validate.notNull(typeInfo);
		this.typeInfo = typeInfo;
	}

	@Override
	public JSAssignmentExpression createTypeInfoDeclaration(MappingCompiler<T, C> mappingCompiler) {
		Validate.notNull(mappingCompiler);
		final Modules<T, C> mappingNameResolver = mappingCompiler.getModules();
		final String mappingName = mappingCompiler.getMapping().getMappingName();
		final JSCodeModel codeModel = mappingCompiler.getCodeModel();
		final String typeInfoMappingName = mappingNameResolver
				.getMappingName(this.typeInfo.getPackageInfo().getPackageName());
		final String spaceName = typeInfoMappingName.equals(mappingName) ? "" : typeInfoMappingName;
		final String typeInfoName = spaceName + "."
				+ this.typeInfo.getContainerLocalName(MappingCompiler.DEFAULT_SCOPED_NAME_DELIMITER);
		return codeModel.string(typeInfoName);

	}
}
