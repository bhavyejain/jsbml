package org.sbml.jsbml.validator.offline.constraints;

import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.CompartmentType;
import org.sbml.jsbml.Constraint;
import org.sbml.jsbml.Event;
import org.sbml.jsbml.FunctionDefinition;
import org.sbml.jsbml.InitialAssignment;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Rule;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.SpeciesType;
import org.sbml.jsbml.UnitDefinition;
import org.sbml.jsbml.ext.SBasePlugin;
import org.sbml.jsbml.util.TreeNodeChangeListener;
import org.sbml.jsbml.validator.offline.ValidationContext;
import org.sbml.jsbml.validator.offline.factory.ConstraintFactory;
import org.sbml.jsbml.validator.offline.factory.FactoryManager;
import org.sbml.jsbml.validator.offline.constraints.ConstraintGroup;


@SuppressWarnings("deprecation")
public class SpecialConstraintBuilder extends AbstractConstraintBuilder {

    @Override
    public AnyConstraint<?> createConstraint(int id) {
	switch (id) {
	case FactoryManager.ID_EMPTY_CONSTRAINT:
	    return new ValidationConstraint<>(id, null);

	case FactoryManager.ID_VALIDATE_DOCUMENT_TREE:
	    ValidationFunction<SBMLDocument> f2 = new ValidationFunction<SBMLDocument>() {
		@Override
		public boolean check(ValidationContext ctx, SBMLDocument t) {
		    /*
		     * Special constraint to validate the hole document tree
		     */
		    System.out.println("validate doc tree");
		    ConstraintFactory factory = ConstraintFactory.getInstance(ctx.getLevel(), ctx.getVersion());

		    AnyConstraint<Model> mc = factory.getConstraintsForClass(Model.class, ctx.getCheckCategories(),
			    ctx.getPackages());
		    mc.check(ctx, t.getModel());

		    return true;
		}
	    };

	    return new ValidationConstraint<SBMLDocument>(id, f2);

	case FactoryManager.ID_VALIDATE_CORE_MODEL_TREE:
	    ValidationFunction<Model> f3 = new ValidationFunction<Model>() {
		@Override
		public boolean check(ValidationContext ctx, Model t) {
		    /*
		     * Special constraint to validate the hole model tree
		     */
		    ConstraintFactory factory = ConstraintFactory.getInstance(ctx.getLevel(), ctx.getVersion());

		    {
			AnyConstraint<Species> sc = factory.getConstraintsForClass(Species.class,
				ctx.getCheckCategories(), ctx.getPackages());

			for (Species s : t.getListOfSpecies()) {
			    sc.check(ctx, s);
			}
		    }

		    {

			AnyConstraint<Compartment> cc = factory.getConstraintsForClass(Compartment.class,
				ctx.getCheckCategories(), ctx.getPackages());

			for (Compartment c : t.getListOfCompartments()) {
			    cc.check(ctx, c);
			}
		    }

		    {

			AnyConstraint<CompartmentType> cc = factory.getConstraintsForClass(CompartmentType.class,
				ctx.getCheckCategories(), ctx.getPackages());

			for (CompartmentType c : t.getListOfCompartmentTypes()) {
			    cc.check(ctx, c);
			}
		    }

		    {
			AnyConstraint<Constraint> cc = factory.getConstraintsForClass(Constraint.class,
				ctx.getCheckCategories(), ctx.getPackages());

			for (Constraint c : t.getListOfConstraints()) {
			    cc.check(ctx, c);
			}
		    }

		    {
			AnyConstraint<Event> cc = factory.getConstraintsForClass(Event.class, ctx.getCheckCategories(),
				ctx.getPackages());

			for (Event c : t.getListOfEvents()) {
			    cc.check(ctx, c);
			}
		    }

		    {
			AnyConstraint<FunctionDefinition> cc = factory.getConstraintsForClass(FunctionDefinition.class,
				ctx.getCheckCategories(), ctx.getPackages());

			for (FunctionDefinition c : t.getListOfFunctionDefinitions()) {
			    cc.check(ctx, c);
			}
		    }

		    {
			AnyConstraint<InitialAssignment> cc = factory.getConstraintsForClass(InitialAssignment.class,
				ctx.getCheckCategories(), ctx.getPackages());

			for (InitialAssignment c : t.getListOfInitialAssignments()) {
			    cc.check(ctx, c);
			}
		    }

		    {
			AnyConstraint<Parameter> cc = factory.getConstraintsForClass(Parameter.class,
				ctx.getCheckCategories(), ctx.getPackages());

			for (Parameter c : t.getListOfParameters()) {
			    cc.check(ctx, c);
			}
		    }

		    {
			AnyConstraint<UnitDefinition> cc = factory.getConstraintsForClass(UnitDefinition.class,
				ctx.getCheckCategories(), ctx.getPackages());

			for (UnitDefinition c : t.getListOfPredefinedUnitDefinitions()) {
			    cc.check(ctx, c);
			}

			for (UnitDefinition ud : t.getListOfUnitDefinitions()) {
			    cc.check(ctx, ud);
			}
		    }

		    {
			AnyConstraint<Reaction> cc = factory.getConstraintsForClass(Reaction.class,
				ctx.getCheckCategories(), ctx.getPackages());

			for (Reaction c : t.getListOfReactions()) {
			    cc.check(ctx, c);
			}
		    }

		    {
			AnyConstraint<Rule> cc = factory.getConstraintsForClass(Rule.class, ctx.getCheckCategories(),
				ctx.getPackages());

			for (Rule c : t.getListOfRules()) {
			    cc.check(ctx, c);
			}
		    }

		    {

			AnyConstraint<SpeciesType> cc = factory.getConstraintsForClass(SpeciesType.class,
				ctx.getCheckCategories(), ctx.getPackages());

			for (SpeciesType c : t.getListOfSpeciesTypes()) {
			    cc.check(ctx, c);
			}
		    }

		    {
			AnyConstraint<TreeNodeChangeListener> cc = factory.getConstraintsForClass(
				TreeNodeChangeListener.class, ctx.getCheckCategories(), ctx.getPackages());

			for (TreeNodeChangeListener c : t.getListOfTreeNodeChangeListeners()) {
			    cc.check(ctx, c);
			}
		    }

		    return true;
		}
	    };

	    return new ValidationConstraint<Model>(id, f3);

//	case FactoryManager.ID_VALIDATE_LAYOUT_MODEL_TREE:
//	    ValidationFunction<Model> f4 = new ValidationFunction<Model>() {
//
//		@Override
//		public boolean check(ValidationContext ctx, Model m) {
//
//		    SBasePlugin extension = null;
//
//		    if (ctx.getLevel() == 3 && ctx.getVersion() == 1) {
//			extension = m.getExtension(LayoutConstants.namespaceURI_L3V1V1);
//		    } else {
//			extension = m.getExtension(LayoutConstants.namespaceURI);
//		    }
//
//		    if (extension instanceof LayoutModelPlugin) {
//			LayoutModelPlugin layoutModel = (LayoutModelPlugin) extension;
//			ConstraintFactory factory = ConstraintFactory.getInstance(ctx.getLevel(), ctx.getVersion());
//
//			{
//			    AnyConstraint<Layout> c = factory.getConstraintsForClass(Layout.class,
//				    ctx.getCheckCategories(), ctx.getPackages());
//
//			    for (Layout l : layoutModel.getListOfLayouts()) {
//				c.check(ctx, l);
//			    }
//			}
//
//		    }
//		    return true;
//		}
//	    };
//
//	    return new ValidationConstraint<Model>(id, f4);
//	case FactoryManager.ID_VALIDATE_LAYOUT_TREE:
//	    ValidationFunction<Layout> f5 = new ValidationFunction<Layout>() {
//
//		@Override
//		public boolean check(ValidationContext ctx, Layout l) {
//		    // TODO Auto-generated method stub
//
//		    ConstraintFactory factory = ConstraintFactory.getInstance(ctx.getLevel(), ctx.getVersion());
//
//		    {
//			AnyConstraint<GraphicalObject> c = factory.getConstraintsForClass(GraphicalObject.class,
//				ctx.getCheckCategories(), ctx.getPackages());
//			
//			for (GraphicalObject go : l.getListOfAdditionalGraphicalObjects())
//			{
//			    c.check(ctx, go);
//			}
//		    }
//		    
//		    {
//			AnyConstraint<CompartmentGlyph> c = factory.getConstraintsForClass(CompartmentGlyph.class,
//				ctx.getCheckCategories(), ctx.getPackages());
//			
//			for (CompartmentGlyph cg : l.getListOfCompartmentGlyphs())
//			{
//			    c.check(ctx, cg);
//			}
//		    }
//		    
//		    {
//			AnyConstraint<ReactionGlyph> c = factory.getConstraintsForClass(ReactionGlyph.class,
//				ctx.getCheckCategories(), ctx.getPackages());
//			
//			for (ReactionGlyph rg : l.getListOfReactionGlyphs())
//			{
//			    c.check(ctx, rg);
//			}
//		    }
//		    
//		    {
//			AnyConstraint<SpeciesGlyph> c = factory.getConstraintsForClass(SpeciesGlyph.class,
//				ctx.getCheckCategories(), ctx.getPackages());
//			
//			for (SpeciesGlyph sg : l.getListOfSpeciesGlyphs())
//			{
//			    c.check(ctx, sg);
//			}
//		    }
//		    
//		    {
//			AnyConstraint<TextGlyph> c = factory.getConstraintsForClass(TextGlyph.class,
//				ctx.getCheckCategories(), ctx.getPackages());
//			
//			for (TextGlyph tg : l.getListOfTextGlyphs())
//			{
//			    c.check(ctx, tg);
//			}
//		    }
//		    
//		    {
//			AnyConstraint<TreeNodeChangeListener> c = factory.getConstraintsForClass(TreeNodeChangeListener.class,
//				ctx.getCheckCategories(), ctx.getPackages());
//			
//			for (TreeNodeChangeListener t : l.getListOfTreeNodeChangeListeners())
//			{
//			    c.check(ctx, t);
//			}
//		    }
//
//		    return true;
//		}
//
//	    };
//
//	    return new ValidationConstraint<Layout>(id, f5);

	default:
	    break;
	}

	return null;
    }

}
