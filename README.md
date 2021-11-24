# LearnDI
Study dagger and DI

### All concepts you need to know
#### @Component
`@Component` tells Dagger to implement an interface or abstract class that creates and returns one or more application objects.
Dagger will generate a class that implements the component type. The generated type will be named DaggerYourType (or DaggerYourType_NestedType for nested types)

#### @Inject
`@Inject` on a constructor tells Dagger how to instantiate that class. We’ll see more shortly.

Parameters to an @Inject constructor are the dependencies of the class. Dagger will provide a class’s dependencies to instantiate the class itself. Note that this is recursive: a dependency may have dependencies of its own!

#### @Modules
`@Modules` are classes or interfaces that act as collections of instructions for Dagger on how to construct dependencies. They’re called modules because they are modular: you can mix and match modules in different applications and contexts.

#### @Binds
`@Binds` methods are one way to tell Dagger how to construct an instance. They are abstract methods on modules that associate one type that Dagger already knows how to construct (the method’s parameter) with a type that Dagger doesn’t yet know how to construct (the method’s return type).

#### @Provides
`@Provides` methods are concrete methods in a module that tell Dagger that when something requests an instance of the type the method returns, it should call that method to get an instance. Like `@Inject` constructors, they can have parameters: those parameters are their dependencies.

`@Provides` methods can contain arbitrary code as long as they return an instance of the provided type. They do not need to create a new instance on each invocation.

    This highlights an important aspect of Dagger (and dependency injection as a whole): when a type is requested, whether or not a new instance is created to satisfy that request is an implementation detail. Going forward, we’ll use the term “provided” instead of “created”, as that is more accurate for what is happening.

#### @IntoMap @IntoSet
`@IntoMap` allows for the creation of a map with values of a specific type, with keys set using special annotations such as @StringKey or `@IntKey`. Because keys are set via annotation, Dagger ensures that multiple values are not mapped to the same key.

`@IntoSet` allows for the creation of a set of types to be collected together. It can be used together with `@Binds` and `@Provides` methods to provide a Set<ReturnType>.

`@IntoMap` and `@IntoSet` are both ways of introducing what is often called “multibindings”, where a collection contains elements from several different binding methods.

#### @Singleton
`@Singleton` instructs Dagger to create only one instance of the type for each instance of the component. It can be used on the class declaration of a type that has an `@Inject` constructor, or on a `@Binds` or `@Provides` method. 
It’s not yet clear why you have to annotate the component with @Singleton as well, but it will become clearer later.

#### @Subcomponent
`@Subcomponent-annotated` type (a “subcomponent”) is, like a `@Component-annotated` one, a factory for an object. Like `@Component`, it uses modules to give Dagger implementation instructions. Subcomponents always have a parent component (or a parent subcomponent), and any objects that are requestable in the parent are requestable in the child, but not vice versa.

A `@Subcomponent.Factory-annotated` type creates instances of the subcomponent. An instance of it is requestable in the parent component.

    There is a parallel annotation, @Component.Factory, for @Component.

#### @BindsInstance
`@BindsInstance` parameters let you make arbitrary objects requestable by other binding methods in the component.

#### @Qualifier
`@Qualifier` annotations are used to differentiate between instances of the same type that are unrelated.

    Contrast this with @IntoSet and @IntoMap, where the collected objects are used together.

Qualifiers are often, but certainly not always, used with common data types such as primitive types and String, which may be used in many places in a program for very different reasons.

#### @Scope
A `@Scope` annotation instructs Dagger to provide one shared instance for all the requests for that type within an instance of the (sub)component that shares the same annotation.

    Note that @Singleton, which we described previously, is really just another scope annotation!

The lifetime of a scoped instance is directly related to the lifetime of the component with that scope.

Note that the name of the scope is meaningless; even multiple instances of a `@Singleton-annotated` type can be created if multiple `@Singleton-annotated` `@Components` are instantiated in a single JVM.

#### @BindsOptionalOf
`@BindsOptionalOf` tells Dagger that it can construct instances of Optional<ReturnType>. The presence of the Optional is determined by whether Dagger knows how to create an instance of ReturnType, and it can be present in a subcomponent but absent in its parent.

When Dagger creates sets or maps of objects, the set or map will contain all values from any parent component(s) in addition to the values that are installed by bindings in the component itself.